package com.vidlearn.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.vidlearn.data.Screen
import com.vidlearn.ui.components.BottomNavigationBar
import com.vidlearn.ui.components.TopBar
import com.vidlearn.ui.components.VoiceCommandDialog
import com.vidlearn.ui.screens.AdminScreen
import com.vidlearn.ui.screens.CertificateScreen
import com.vidlearn.ui.screens.CourseDetailScreen
import com.vidlearn.ui.screens.CreateCourseScreen
import com.vidlearn.ui.screens.FavoritesScreen
import com.vidlearn.ui.screens.HomeScreen
import com.vidlearn.ui.screens.LearnScreen
import com.vidlearn.ui.screens.LoginScreen
import com.vidlearn.ui.screens.MyLearningScreen
import com.vidlearn.ui.screens.SplashScreen

@Composable
fun VidlearnApp(
    viewModel: VidlearnViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val allCourses = viewModel.getAllCourses()
    val filteredCourses = viewModel.getFilteredCourses()

    if (uiState.showSplash) {
        SplashScreen(onSplashFinished = { viewModel.finishSplash() })
    } else {
        val showTopBar = uiState.currentScreen in listOf(
            Screen.HOME,
            Screen.MY_LEARNING,
            Screen.FAVORITES,
            Screen.CREATE_COURSE,
            Screen.LOGIN,
            Screen.ADMIN
        )

        val showBottomBar = uiState.currentScreen !in listOf(
            Screen.LEARN,
            Screen.CERTIFICATE,
            Screen.COURSE_DETAIL
        )

        Scaffold(
            topBar = {
                if (showTopBar) {
                    TopBar(
                        searchQuery = uiState.searchQuery,
                        onSearchQueryChange = { viewModel.setSearchQuery(it) },
                        onLogoClick = { viewModel.navigateTo(Screen.HOME) },
                        onFavoritesClick = { viewModel.navigateTo(Screen.FAVORITES) },
                        onUserClick = { viewModel.navigateTo(Screen.LOGIN) },
                        userName = uiState.user
                    )
                }
            },
            bottomBar = {
                if (showBottomBar) {
                    BottomNavigationBar(
                        currentScreen = uiState.currentScreen,
                        onNavigate = { viewModel.navigateTo(it) },
                        userEmail = uiState.userEmail,
                        userName = uiState.user
                    )
                }
            }
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                when (uiState.currentScreen) {
                    Screen.HOME -> {
                        HomeScreen(
                            uiState = uiState,
                            filteredCourses = filteredCourses,
                            allCourses = allCourses,
                            onSelectCategory = { viewModel.selectCategory(it) },
                            onSelectLevel = { viewModel.selectLevel(it) },
                            onCourseClick = { viewModel.selectCourse(it) },
                            onFavoriteToggle = { viewModel.toggleFavorite(it) }
                        )
                    }
                    Screen.MY_LEARNING -> {
                        MyLearningScreen(
                            uiState = uiState,
                            allCourses = allCourses,
                            onCourseClick = { viewModel.selectCourse(it) },
                            onFavoriteToggle = { viewModel.toggleFavorite(it) }
                        )
                    }
                    Screen.FAVORITES -> {
                        FavoritesScreen(
                            uiState = uiState,
                            allCourses = allCourses,
                            onCourseClick = { viewModel.selectCourse(it) },
                            onFavoriteToggle = { viewModel.toggleFavorite(it) }
                        )
                    }
                    Screen.COURSE_DETAIL -> {
                        val course = uiState.selectedCourse
                        if (course != null) {
                            val isEnrolled = uiState.enrolledIds.contains(course.id)
                            CourseDetailScreen(
                                course = course,
                                isEnrolled = isEnrolled,
                                onBackClick = { viewModel.navigateTo(Screen.HOME) },
                                onStartCourse = { viewModel.startCourse(course) }
                            )
                        }
                    }
                    Screen.LEARN -> {
                        val course = uiState.selectedCourse
                        if (course != null) {
                            LearnScreen(
                                course = course,
                                currentLessonIndex = uiState.currentLessonIndex,
                                completedLessons = uiState.completedLessons,
                                currentCode = viewModel.getCurrentLessonCode(),
                                onCodeChange = { viewModel.updateLessonCode(it) },
                                checkResult = uiState.checkResult,
                                onRunCheck = { viewModel.runAndCheckCode() },
                                onSelectLesson = { viewModel.setLessonIndex(it) },
                                isUnlocked = { idx -> viewModel.isLessonUnlocked(course.id, idx) },
                                onNextLesson = { viewModel.nextLesson() },
                                onGetCertificate = { viewModel.navigateTo(Screen.CERTIFICATE) },
                                onBackClick = { viewModel.navigateTo(Screen.COURSE_DETAIL) },
                                learnTab = uiState.learnTab,
                                onTabSelect = { viewModel.setLearnTab(it) },
                                currentNote = viewModel.getCurrentLessonNote(),
                                onNoteChange = { viewModel.updateLessonNote(it) },
                                videoSpeed = uiState.videoSpeed,
                                onSpeedChange = { viewModel.setVideoSpeed(it) },
                                onAddQnAQuestion = { viewModel.addQnAQuestion(it) }
                            )
                        }
                    }
                    Screen.CERTIFICATE -> {
                        val course = uiState.selectedCourse
                        if (course != null) {
                            CertificateScreen(
                                course = course,
                                userName = uiState.user,
                                onBackClick = { viewModel.navigateTo(Screen.LEARN) }
                            )
                        }
                    }
                    Screen.LOGIN -> {
                        LoginScreen(
                            userName = uiState.user,
                            userEmail = uiState.userEmail,
                            onLogin = { name, email -> viewModel.login(name, email) },
                            onLogout = { viewModel.logout() },
                            onOpenAdmin = { viewModel.navigateToAdmin() }
                        )
                    }
                    Screen.CREATE_COURSE -> {
                        CreateCourseScreen(
                            userName = uiState.user,
                            onLoginClick = { viewModel.navigateTo(Screen.LOGIN) },
                            onCreateCourse = { title, desc, cat, price, rows ->
                                viewModel.createCourse(title, desc, cat, price, rows)
                            }
                        )
                    }
                    Screen.ADMIN -> {
                        AdminScreen(
                            uiState = uiState,
                            allCourses = allCourses,
                            onBackClick = { viewModel.navigateTo(Screen.HOME) },
                            onDeleteCustomCourse = { id -> viewModel.deleteCustomCourse(id) },
                            onCreateCourse = { title, desc, cat, price, rows ->
                                viewModel.createCourse(title, desc, cat, price, rows)
                            }
                        )
                    }
                }
            }

            if (uiState.showVoiceDialog) {
                VoiceCommandDialog(
                    onDismiss = { viewModel.setShowVoiceDialog(false) },
                    onVoiceCodeSubmit = { code -> viewModel.verifyVoiceCode(code) }
                )
            }
        }
    }
}
