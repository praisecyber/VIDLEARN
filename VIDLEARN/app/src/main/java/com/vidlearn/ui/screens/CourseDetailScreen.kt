package com.vidlearn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.data.Course
import com.vidlearn.ui.components.CourseThumbnail
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.LightBorder
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen
import com.vidlearn.ui.components.StarRatingBar

@Composable
fun CourseDetailScreen(
    course: Course,
    isEnrolled: Boolean,
    onBackClick: () -> Unit,
    onStartCourse: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        // Dark Header Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkCharcoal)
                .padding(20.dp)
        ) {
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${course.category}  ›  Videos",
                        color = Color(0xFF7BE0A8),
                        fontSize = 13.sp
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = course.title,
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 30.sp
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = course.description,
                    color = Color(0xFFEEEEEE),
                    fontSize = 14.sp,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Badges Row
                Row(verticalAlignment = Alignment.CenterVertically) {
                    if (course.isBestseller) {
                        Text(
                            text = "Bestseller",
                            color = Color(0xFF0A5A38),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(Color(0xFFC6F0DD), RoundedCornerShape(2.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    if (course.isHotAndNew) {
                        Text(
                            text = "Hot & New",
                            color = Color(0xFF8A1C1C),
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .background(Color(0xFFFDE4E4), RoundedCornerShape(2.dp))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                    }
                    Text(
                        text = course.level,
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = course.rating.toString(),
                        color = Color(0xFFF3CA8C),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    StarRatingBar(rating = course.rating)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "(${course.ratingsCount} ratings)",
                        color = Color(0xFFEEEEEE),
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.Language,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "English",
                        color = Color(0xFFCCCCCC),
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "Created by ${course.instructor}",
                        color = Color(0xFFCCCCCC),
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Thumbnail & Action Box
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                    .padding(12.dp)
            ) {
                Column {
                    CourseThumbnail(course = course)
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(
                        text = course.price,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = onStartCourse,
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                        shape = RoundedCornerShape(2.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = if (isEnrolled) "Go to course" else "Enroll now",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "30-day money-back guarantee · Full lifetime access",
                        color = MutedText,
                        fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // What you'll learn
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF7F9FA))
                    .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "What you'll learn",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkCharcoal
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    course.lessons.forEach { lesson ->
                        Row(
                            verticalAlignment = Alignment.Top,
                            modifier = Modifier.padding(vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = null,
                                tint = PrimaryGreen,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Master ${lesson.title.lowercase()}: ${lesson.task}",
                                fontSize = 13.sp,
                                color = DarkCharcoal,
                                lineHeight = 18.sp
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Course content summary
            Text(
                text = "Course content",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${course.lessons.size} lectures · ${course.duration}",
                color = MutedText,
                fontSize = 13.sp,
                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            )

            course.lessons.forEachIndexed { index, lesson ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, LightBorder)
                        .background(Color.White)
                        .padding(14.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.PlayArrow,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "${index + 1}. ${lesson.title}",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = DarkCharcoal,
                        modifier = Modifier.weight(1f)
                    )
                    Text(
                        text = lesson.duration,
                        fontSize = 12.sp,
                        color = MutedText
                    )
                }
            }

            // Requirements Section
            if (course.requirements.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Requirements", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                course.requirements.forEach { req ->
                    Row(modifier = Modifier.padding(vertical = 3.dp)) {
                        Text(text = "• ", fontWeight = FontWeight.Bold, color = PrimaryGreen)
                        Text(text = req, fontSize = 14.sp, color = DarkCharcoal)
                    }
                }
            }

            // Instructor Section
            Spacer(modifier = Modifier.height(24.dp))
            Text(text = "Instructor", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = course.instructor,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = PrimaryGreen
            )
            Text(
                text = course.instructorTitle,
                fontSize = 13.sp,
                color = MutedText
            )
            Spacer(modifier = Modifier.height(10.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(DarkCharcoal)
                ) {
                    Icon(Icons.Filled.Person, contentDescription = null, tint = Color.White, modifier = Modifier.size(32.dp))
                }
                Spacer(modifier = Modifier.width(14.dp))
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Star, contentDescription = null, tint = Color(0xFFE59819), modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "${course.rating} Instructor Rating", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 2.dp)) {
                        Icon(Icons.Filled.WorkspacePremium, contentDescription = null, tint = DarkCharcoal, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = "${course.ratingsCount} Reviews", fontSize = 13.sp)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 2.dp)) {
                        Icon(Icons.Filled.Group, contentDescription = null, tint = DarkCharcoal, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(text = course.instructorStudentsCount, fontSize = 13.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = course.instructorBio,
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = DarkCharcoal
            )

            // Student Reviews Section
            if (course.reviews.isNotEmpty()) {
                Spacer(modifier = Modifier.height(24.dp))
                Text(text = "Student Reviews", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(10.dp))

                course.reviews.forEach { review ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp)
                            .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                            .padding(12.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(text = review.userName, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                                Spacer(modifier = Modifier.weight(1f))
                                Text(text = review.date, fontSize = 11.sp, color = MutedText)
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            StarRatingBar(rating = review.rating)
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(text = review.comment, fontSize = 13.sp, color = DarkCharcoal, lineHeight = 18.sp)
                        }
                    }
                }
            }
        }
    }
}
