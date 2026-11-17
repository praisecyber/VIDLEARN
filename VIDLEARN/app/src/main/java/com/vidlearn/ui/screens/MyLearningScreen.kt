package com.vidlearn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.data.Course
import com.vidlearn.ui.VidlearnUiState
import com.vidlearn.ui.components.CourseCard
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.LightBorder
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen

@Composable
fun MyLearningScreen(
    uiState: VidlearnUiState,
    allCourses: List<Course>,
    onCourseClick: (Course) -> Unit,
    onFavoriteToggle: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val enrolledCourses = allCourses.filter { uiState.enrolledIds.contains(it.id) }
    val wishlistCourses = allCourses.filter { uiState.favoriteIds.contains(it.id) }

    val completedCourses = enrolledCourses.filter { c ->
        c.lessons.isNotEmpty() && c.lessons.all { (idx) ->
            uiState.completedLessons.contains("${c.id}-$idx")
        }
    }
    val inProgressCourses = enrolledCourses.filter { c -> !completedCourses.contains(c) }

    var selectedTab by remember { mutableStateOf("All Courses") }
    val tabs = listOf("All Courses", "In Progress", "Completed", "Wishlist")

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "My learning",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkCharcoal
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Progress Summary Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(4.dp))
                .background(DarkCharcoal)
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "${enrolledCourses.size} Enrolled Courses",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${completedCourses.size} Certificates Earned",
                        color = Color(0xFF7BE0A8),
                        fontSize = 13.sp,
                        modifier = Modifier.padding(top = 2.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .background(PrimaryGreen, RoundedCornerShape(4.dp))
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    Text(
                        text = "${inProgressCourses.size} In Progress",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Tabs
        TabRow(
            selectedTabIndex = tabs.indexOf(selectedTab),
            containerColor = Color.White,
            contentColor = DarkCharcoal
        ) {
            tabs.forEach { tabName ->
                Tab(
                    selected = selectedTab == tabName,
                    onClick = { selectedTab = tabName },
                    text = {
                        Text(
                            text = tabName,
                            fontWeight = if (selectedTab == tabName) FontWeight.Bold else FontWeight.Normal,
                            color = if (selectedTab == tabName) DarkCharcoal else MutedText,
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        val displayList = when (selectedTab) {
            "In Progress" -> inProgressCourses
            "Completed" -> completedCourses
            "Wishlist" -> wishlistCourses
            else -> enrolledCourses
        }

        if (displayList.isEmpty()) {
            Text(
                text = "No courses found in $selectedTab.",
                color = MutedText,
                fontSize = 14.sp,
                modifier = Modifier.padding(vertical = 16.dp)
            )
        } else {
            displayList.forEach { course ->
                val isEnrolled = uiState.enrolledIds.contains(course.id)
                val completedCount = course.lessons.count { (idx) ->
                    uiState.completedLessons.contains("${course.id}-$idx")
                }
                CourseCard(
                    course = course,
                    isEnrolled = isEnrolled,
                    completedCount = completedCount,
                    isFavorite = uiState.favoriteIds.contains(course.id),
                    onCourseClick = { onCourseClick(course) },
                    onFavoriteToggle = { onFavoriteToggle(course.id) }
                )
            }
        }
    }
}
