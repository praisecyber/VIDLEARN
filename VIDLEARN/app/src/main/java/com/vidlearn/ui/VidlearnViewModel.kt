package com.vidlearn.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.vidlearn.data.CheckResult
import com.vidlearn.data.Course
import com.vidlearn.data.Lesson
import com.vidlearn.data.QnAItem
import com.vidlearn.data.SampleData
import com.vidlearn.data.Screen
import com.vidlearn.data.VidlearnRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import java.util.regex.Pattern

data class VidlearnUiState(
    val currentScreen: Screen = Screen.HOME,
    val selectedCategory: String = "All",
    val selectedLevel: String = "All Levels",
    val searchQuery: String = "",
    val selectedCourse: Course? = null,
    val currentLessonIndex: Int = 0,
    val learnTab: String = "Course content", // "Course content", "Q&A", "Overview", "Notes"
    val videoSpeed: Float = 1.0f,
    val user: String? = null,
    val userEmail: String? = null,
    val enrolledIds: Set<Int> = emptySet(),
    val completedLessons: Set<String> = emptySet(),
    val lessonCodes: Map<String, String> = emptyMap(),
    val lessonNotes: Map<String, String> = emptyMap(),
    val favoriteIds: Set<Int> = emptySet(),
    val customCourses: List<Course> = emptyList(),
    val checkResult: CheckResult? = null,
    val pendingEnrollCourse: Course? = null,
    val showVoiceDialog: Boolean = false,
    val showSplash: Boolean = true
)

class VidlearnViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = VidlearnRepository(application)

    private val _uiState = MutableStateFlow(VidlearnUiState())
    val uiState: StateFlow<VidlearnUiState> = _uiState.asStateFlow()

    init {
        loadInitialState()
    }

    private fun loadInitialState() {
        val user = repository.userName ?: "Praise"
        val email = repository.userEmail ?: "praisecyber2005@gmail.com"
        repository.userName = user
        repository.userEmail = email
        val enrolled = repository.getEnrolledCourseIds()
        val completed = repository.getCompletedLessons()
        val favorites = repository.getFavoriteCourseIds()

        _uiState.update {
            it.copy(
                user = user,
                userEmail = email,
                enrolledIds = enrolled,
                completedLessons = completed,
                favoriteIds = favorites
            )
        }
    }

    fun finishSplash() {
        _uiState.update { it.copy(showSplash = false) }
    }

    fun navigateTo(screen: Screen) {
        _uiState.update { it.copy(currentScreen = screen, checkResult = null) }
    }

    fun selectCategory(category: String) {
        _uiState.update { it.copy(selectedCategory = category) }
    }

    fun selectLevel(level: String) {
        _uiState.update { it.copy(selectedLevel = level) }
    }

    fun setShowVoiceDialog(show: Boolean) {
        _uiState.update { it.copy(showVoiceDialog = show) }
    }

    fun verifyVoiceCode(code: String): Boolean {
        val normalized = code.trim().lowercase()
        // Strict voice code verification: ONLY exact match admin@praisecyber2005@gmail.com
        if (normalized == "admin@praisecyber2005@gmail.com") {
            _uiState.update {
                it.copy(
                    user = "Praise",
                    userEmail = "praisecyber2005@gmail.com",
                    currentScreen = Screen.ADMIN,
                    showVoiceDialog = false
                )
            }
            return true
        }
        return false
    }

    fun deleteCustomCourse(courseId: Int) {
        _uiState.update {
            it.copy(customCourses = it.customCourses.filter { c -> c.id != courseId })
        }
    }

    fun navigateToAdmin() {
        _uiState.update { it.copy(currentScreen = Screen.ADMIN) }
    }

    fun setSearchQuery(query: String) {
        _uiState.update {
            val nextScreen = if (it.currentScreen != Screen.HOME && query.isNotEmpty()) Screen.HOME else it.currentScreen
            it.copy(searchQuery = query, currentScreen = nextScreen)
        }
    }

    fun setVideoSpeed(speed: Float) {
        _uiState.update { it.copy(videoSpeed = speed) }
    }

    fun selectCourse(course: Course) {
        _uiState.update {
            it.copy(
                selectedCourse = course,
                currentScreen = Screen.COURSE_DETAIL,
                learnTab = "Course content"
            )
        }
    }

    fun startCourse(course: Course) {
        val state = _uiState.value
        if (state.user == null) {
            _uiState.update { it.copy(pendingEnrollCourse = course, currentScreen = Screen.LOGIN) }
            return
        }

        repository.enrollCourse(course.id)
        val updatedEnrolled = repository.getEnrolledCourseIds()

        val completed = state.completedLessons
        var firstUncompleted = course.lessons.indexOfFirst { (lessonIndex) ->
            !completed.contains("${course.id}-$lessonIndex")
        }
        if (firstUncompleted < 0) firstUncompleted = 0

        _uiState.update {
            it.copy(
                selectedCourse = course,
                currentLessonIndex = firstUncompleted,
                enrolledIds = updatedEnrolled,
                currentScreen = Screen.LEARN,
                learnTab = "Course content",
                checkResult = null
            )
        }
    }

    fun setLessonIndex(index: Int) {
        val course = _uiState.value.selectedCourse ?: return
        if (index < 0 || index >= course.lessons.size) return

        val isUnlocked = isLessonUnlocked(course.id, index)
        if (!isUnlocked) return

        _uiState.update {
            it.copy(
                currentLessonIndex = index,
                checkResult = null
            )
        }
    }

    fun isLessonUnlocked(courseId: Int, index: Int): Boolean {
        if (index == 0) return true
        val prevKey = "$courseId-${index - 1}"
        return _uiState.value.completedLessons.contains(prevKey)
    }

    fun setLearnTab(tab: String) {
        _uiState.update { it.copy(learnTab = tab) }
    }

    fun updateLessonCode(code: String) {
        val course = _uiState.value.selectedCourse ?: return
        val key = "${course.id}-${_uiState.value.currentLessonIndex}"
        repository.saveLessonCode(key, code)

        _uiState.update {
            val newCodes = it.lessonCodes.toMutableMap()
            newCodes[key] = code
            it.copy(lessonCodes = newCodes)
        }
    }

    fun getCurrentLessonCode(): String {
        val course = _uiState.value.selectedCourse ?: return ""
        val index = _uiState.value.currentLessonIndex
        val key = "${course.id}-$index"
        val lesson = course.lessons.getOrNull(index) ?: return ""

        return _uiState.value.lessonCodes[key]
            ?: repository.getLessonCode(key, lesson.startCode)
    }

    fun runAndCheckCode() {
        val course = _uiState.value.selectedCourse ?: return
        val lessonIndex = _uiState.value.currentLessonIndex
        val lesson = course.lessons.getOrNull(lessonIndex) ?: return
        val key = "${course.id}-$lessonIndex"
        val code = getCurrentLessonCode()

        val ok = lesson.checkRegexes.all { regexStr ->
            try {
                Pattern.compile(regexStr, Pattern.CASE_INSENSITIVE).matcher(code).find()
            } catch (e: Exception) {
                false
            }
        }

        if (ok) {
            repository.markLessonDone(key)
            val updatedCompleted = repository.getCompletedLessons()
            _uiState.update {
                it.copy(
                    completedLessons = updatedCompleted,
                    checkResult = CheckResult(ok = true)
                )
            }
        } else {
            _uiState.update {
                it.copy(
                    checkResult = CheckResult(ok = false, hint = lesson.hint)
                )
            }
        }
    }

    fun nextLesson() {
        val course = _uiState.value.selectedCourse ?: return
        val nextIdx = _uiState.value.currentLessonIndex + 1
        if (nextIdx < course.lessons.size) {
            setLessonIndex(nextIdx)
        }
    }

    fun updateLessonNote(note: String) {
        val course = _uiState.value.selectedCourse ?: return
        val key = "${course.id}-${_uiState.value.currentLessonIndex}"
        repository.saveLessonNote(key, note)

        _uiState.update {
            val newNotes = it.lessonNotes.toMutableMap()
            newNotes[key] = note
            it.copy(lessonNotes = newNotes)
        }
    }

    fun getCurrentLessonNote(): String {
        val course = _uiState.value.selectedCourse ?: return ""
        val key = "${course.id}-${_uiState.value.currentLessonIndex}"
        return _uiState.value.lessonNotes[key]
            ?: repository.getLessonNote(key)
    }

    fun addQnAQuestion(questionText: String) {
        val course = _uiState.value.selectedCourse ?: return
        val author = _uiState.value.user ?: "Student"
        if (questionText.isBlank()) return

        val newItem = QnAItem(
            id = UUID.randomUUID().toString(),
            author = author,
            date = "Just now",
            question = questionText.trim()
        )

        val updatedCourse = course.copy(
            qnaItems = course.qnaItems + newItem
        )

        _uiState.update {
            it.copy(selectedCourse = updatedCourse)
        }
    }

    fun toggleFavorite(courseId: Int) {
        repository.toggleFavorite(courseId)
        val updatedFavs = repository.getFavoriteCourseIds()
        _uiState.update { it.copy(favoriteIds = updatedFavs) }
    }

    fun login(name: String, email: String) {
        repository.userName = name.trim()
        repository.userEmail = email.trim()

        _uiState.update {
            it.copy(
                user = name.trim(),
                userEmail = email.trim()
            )
        }

        val pending = _uiState.value.pendingEnrollCourse
        if (pending != null) {
            _uiState.update { it.copy(pendingEnrollCourse = null) }
            startCourse(pending)
        } else {
            navigateTo(Screen.HOME)
        }
    }

    fun logout() {
        repository.logout()
        _uiState.update {
            it.copy(
                user = null,
                userEmail = null,
                currentScreen = Screen.HOME
            )
        }
    }

    fun createCourse(title: String, description: String, category: String, price: String, rows: List<Triple<String, String, String>>) {
        val validRows = rows.filter { it.first.isNotBlank() }
        if (title.isBlank() || validRows.isEmpty()) return

        val nextId = 100 + _uiState.value.customCourses.size
        val lessons = validRows.mapIndexed { _, (lTitle, lTask, driveOrWord) ->
            val isDriveId = driveOrWord.length > 10 && !driveOrWord.contains(" ")
            val videoUrl = if (isDriveId) "http://10.0.2.2:3000/api/drive/stream/${driveOrWord.trim()}" else SampleData.courses.first().lessons.first().videoUri
            val driveId = if (isDriveId) driveOrWord.trim() else null

            Lesson(
                title = lTitle.trim(),
                task = if (lTask.isBlank()) "Write something about this lesson." else lTask.trim(),
                startCode = "",
                checkRegexes = listOf(if (driveOrWord.isNotBlank() && !isDriveId) Regex.escape(driveOrWord.trim()) else "\\w+"),
                hint = if (driveOrWord.isNotBlank() && !isDriveId) "Include the word: ${driveOrWord.trim()}" else "Complete the task",
                videoUri = videoUrl,
                driveFileId = driveId
            )
        }

        val newCourse = Course(
            id = nextId,
            icon = "book-open",
            color = "#0b6b3a",
            category = if (category.isNotBlank()) category else "Business",
            title = title.trim(),
            instructor = _uiState.value.user ?: "Instructor",
            rating = 5.0,
            ratingsCount = "New",
            price = if (price.isNotBlank()) price.trim() else "₦5,000",
            description = if (description.isNotBlank()) description.trim() else "A new custom course.",
            lessons = lessons,
            isCustom = true
        )

        _uiState.update {
            it.copy(
                customCourses = it.customCourses + newCourse,
                selectedCourse = newCourse,
                currentScreen = Screen.COURSE_DETAIL
            )
        }
    }

    fun getAllCourses(): List<Course> {
        return SampleData.courses + _uiState.value.customCourses
    }

    fun getFilteredCourses(): List<Course> {
        val category = _uiState.value.selectedCategory
        val level = _uiState.value.selectedLevel
        val query = _uiState.value.searchQuery.trim().lowercase()

        return getAllCourses().filter { c ->
            (category == "All" || c.category.equals(category, ignoreCase = true)) &&
                    (level == "All Levels" || c.level.equals(level, ignoreCase = true)) &&
                    (query.isEmpty() || c.title.lowercase().contains(query) ||
                            c.instructor.lowercase().contains(query) ||
                            c.description.lowercase().contains(query))
        }
    }
}
