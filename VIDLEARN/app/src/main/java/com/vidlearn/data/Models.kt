package com.vidlearn.data

data class Lesson(
    val title: String,
    val task: String,
    val startCode: String,
    val checkRegexes: List<String>,
    val hint: String,
    val videoUri: String,
    val duration: String = "10 mins"
)

data class CourseReview(
    val userName: String,
    val rating: Double,
    val date: String,
    val comment: String
)

data class QnAItem(
    val id: String,
    val author: String,
    val date: String,
    val question: String,
    val answers: List<String> = emptyList()
)

data class Course(
    val id: Int,
    val icon: String,
    val color: String,
    val category: String,
    val title: String,
    val instructor: String,
    val rating: Double,
    val ratingsCount: String,
    val price: String,
    val isBestseller: Boolean = false,
    val isHotAndNew: Boolean = false,
    val isHighestRated: Boolean = false,
    val level: String = "All Levels",
    val duration: String = "4.5 total hours",
    val description: String,
    val requirements: List<String> = emptyList(),
    val targetAudience: List<String> = emptyList(),
    val instructorTitle: String = "Professional Instructor & Industry Expert",
    val instructorBio: String = "Passionate educator with years of real-world industry experience helping thousands of students master modern skills.",
    val instructorStudentsCount: String = "45,000+ Students",
    val reviews: List<CourseReview> = emptyList(),
    val qnaItems: List<QnAItem> = emptyList(),
    val lessons: List<Lesson>,
    val imageUrl: String = "",
    val isCustom: Boolean = false
)

enum class Screen {
    HOME,
    MY_LEARNING,
    FAVORITES,
    COURSE_DETAIL,
    LEARN,
    CERTIFICATE,
    LOGIN,
    CREATE_COURSE,
    ADMIN
}

data class CheckResult(
    val ok: Boolean,
    val hint: String = ""
)
