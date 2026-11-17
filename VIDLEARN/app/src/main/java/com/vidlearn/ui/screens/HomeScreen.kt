package com.vidlearn.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.data.Course
import com.vidlearn.data.SampleData
import com.vidlearn.ui.VidlearnUiState
import com.vidlearn.ui.components.CourseCard
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.LightBorder
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen

import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    uiState: VidlearnUiState,
    filteredCourses: List<Course>,
    allCourses: List<Course>,
    onSelectCategory: (String) -> Unit,
    onSelectLevel: (String) -> Unit,
    onCourseClick: (Course) -> Unit,
    onFavoriteToggle: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val showScrollToTop by remember {
        derivedStateOf { scrollState.value > 250 }
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(bottom = 24.dp)
        ) {
            // Category Selector Row
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .border(1.dp, LightBorder)
            ) {
                items(SampleData.categories) { category ->
                    val isSelected = category == uiState.selectedCategory
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isSelected) PrimaryGreen else Color.White)
                            .border(1.dp, if (isSelected) PrimaryGreen else LightBorder, RoundedCornerShape(4.dp))
                            .clickable { onSelectCategory(category) }
                            .padding(horizontal = 14.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = category,
                            color = if (isSelected) Color.White else DarkCharcoal,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            // Level Selector Row
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF7F9FA))
                    .border(0.5.dp, LightBorder)
            ) {
                items(SampleData.levels) { level ->
                    val isSelected = level == uiState.selectedLevel
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(if (isSelected) DarkCharcoal else Color.White)
                            .border(1.dp, if (isSelected) DarkCharcoal else LightBorder, RoundedCornerShape(16.dp))
                            .clickable { onSelectLevel(level) }
                            .padding(horizontal = 12.dp, vertical = 5.dp)
                    ) {
                        Text(
                            text = level,
                            color = if (isSelected) Color.White else DarkCharcoal,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Hero Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(4.dp))
                    .background(DarkCharcoal)
                    .padding(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(160.dp)
                        .background(PrimaryGreen.copy(alpha = 0.85f), CircleShape)
                )

                Column(modifier = Modifier.fillMaxWidth(0.85f)) {
                    Text(
                        text = "Learn by watching and doing",
                        color = Color.White,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.ExtraBold,
                        lineHeight = 30.sp
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Short video lessons with a practice sandbox. Pass the practice to unlock the next video.",
                        color = Color(0xFFDDDDDD),
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }

            // Enrolled Courses section ("Let's start learning")
            val enrolledCourses = allCourses.filter { uiState.enrolledIds.contains(it.id) }
            if (enrolledCourses.isNotEmpty()) {
                Text(
                    text = "Let's start learning",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 12.dp)
                )

                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    enrolledCourses.forEach { course ->
                        val completedCount = course.lessons.count { (idx) ->
                            uiState.completedLessons.contains("${course.id}-$idx")
                        }
                        CourseCard(
                            course = course,
                            isEnrolled = true,
                            completedCount = completedCount,
                            isFavorite = uiState.favoriteIds.contains(course.id),
                            onCourseClick = { onCourseClick(course) },
                            onFavoriteToggle = { onFavoriteToggle(course.id) }
                        )
                    }
                }
            }

            // All / Filtered Courses section
            Text(
                text = "A broad selection of courses",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 16.dp, top = 24.dp, end = 16.dp, bottom = 4.dp)
            )

            Text(
                text = "Choose from ${allCourses.size} video courses",
                color = MutedText,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
            )

            if (filteredCourses.isEmpty()) {
                Text(
                    text = "No courses found matching your criteria.",
                    color = MutedText,
                    modifier = Modifier.padding(16.dp)
                )
            } else {
                Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                    filteredCourses.forEach { course ->
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

            Spacer(modifier = Modifier.height(40.dp))
        }

        // Scroll to Top Floating Action Button
        AnimatedVisibility(
            visible = showScrollToTop,
            enter = fadeIn(),
            exit = fadeOut(),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            FloatingActionButton(
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollTo(0)
                    }
                },
                containerColor = PrimaryGreen,
                contentColor = Color.White,
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Scroll to top",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}
