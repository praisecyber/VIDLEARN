package com.vidlearn.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.data.Course
import com.vidlearn.ui.VidlearnUiState
import com.vidlearn.ui.components.CourseCard
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.MutedText

@Composable
fun FavoritesScreen(
    uiState: VidlearnUiState,
    allCourses: List<Course>,
    onCourseClick: (Course) -> Unit,
    onFavoriteToggle: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteCourses = allCourses.filter { uiState.favoriteIds.contains(it.id) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Saved courses",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkCharcoal
        )
        Text(
            text = "Your favorite courses, saved to your account.",
            color = MutedText,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        if (favoriteCourses.isEmpty()) {
            Text(
                text = "No saved courses yet. Tap the heart icon on any course to save it.",
                color = MutedText,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            favoriteCourses.forEach { course ->
                val isEnrolled = uiState.enrolledIds.contains(course.id)
                val completedCount = course.lessons.count { (idx) ->
                    uiState.completedLessons.contains("${course.id}-$idx")
                }
                CourseCard(
                    course = course,
                    isEnrolled = isEnrolled,
                    completedCount = completedCount,
                    isFavorite = true,
                    onCourseClick = { onCourseClick(course) },
                    onFavoriteToggle = { onFavoriteToggle(course.id) }
                )
            }
        }
    }
}
