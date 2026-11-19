package com.vidlearn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.LightBorder
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen

@Composable
fun AdminScreen(
    uiState: VidlearnUiState,
    allCourses: List<Course>,
    onBackClick: () -> Unit,
    onDeleteCustomCourse: (Int) -> Unit,
    onCreateCourse: (String, String, String, String, List<Triple<String, String, String>>) -> Unit,
    modifier: Modifier = Modifier
) {
    // Strict Admin check: ONLY praisecyber2005@gmail.com
    val isAdmin = uiState.userEmail?.equals("praisecyber2005@gmail.com", ignoreCase = true) == true

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = DarkCharcoal
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Admin Control Panel",
                fontSize = 22.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkCharcoal
            )
        }

        if (!isAdmin) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFFEBEE), RoundedCornerShape(4.dp))
                    .border(1.dp, Color(0xFFEF9A9A), RoundedCornerShape(4.dp))
                    .padding(20.dp)
            ) {
                Column {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Filled.AdminPanelSettings,
                            contentDescription = null,
                            tint = Color(0xFFC62828),
                            modifier = Modifier.size(28.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Access Restricted",
                            color = Color(0xFFC62828),
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Only authorized administrators can access the admin panel.",
                        color = DarkCharcoal,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }
            }
        } else {
            // Admin Authorized View
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE7F4EC), RoundedCornerShape(4.dp))
                    .border(1.dp, PrimaryGreen, RoundedCornerShape(4.dp))
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Filled.VerifiedUser,
                        contentDescription = null,
                        tint = PrimaryGreen,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Authenticated as Administrator (Praise)",
                            color = PrimaryGreen,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = "Full platform management & course publishing enabled.",
                            color = MutedText,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Platform Statistics",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Stat Card 1
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFF7F9FA), RoundedCornerShape(4.dp))
                        .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Icon(
                            imageVector = Icons.Filled.School,
                            contentDescription = null,
                            tint = PrimaryGreen,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "${allCourses.size}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Total Courses", fontSize = 12.sp, color = MutedText)
                    }
                }

                // Stat Card 2
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .background(Color(0xFFF7F9FA), RoundedCornerShape(4.dp))
                        .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                        .padding(14.dp)
                ) {
                    Column {
                        Icon(
                            imageVector = Icons.Filled.People,
                            contentDescription = null,
                            tint = PrimaryGreen,
                            modifier = Modifier.size(22.dp)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "${uiState.enrolledIds.size + 42000}", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Enrolled Students", fontSize = 12.sp, color = MutedText)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Add New Course Form for Admin
            AddCourseSection(onCreateCourse = onCreateCourse)

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Manage Courses & Content",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            AllCourseItems(allCourses = allCourses, onDeleteCustomCourse = onDeleteCustomCourse)
        }
    }
}

@Composable
fun AddCourseSection(
    onCreateCourse: (String, String, String, String, List<Triple<String, String, String>>) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("Code") }
    var price by remember { mutableStateOf("₦9,500") }
    var description by remember { mutableStateOf("") }
    var lessonTitle by remember { mutableStateOf("") }
    var lessonTask by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(4.dp))
            .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Text(
            text = "Publish New Course",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = DarkCharcoal
        )
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Course Title") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = LightBorder
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = category,
                onValueChange = { category = it },
                label = { Text("Category") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = LightBorder
                ),
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = LightBorder
                ),
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = LightBorder
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lessonTitle,
            onValueChange = { lessonTitle = it },
            label = { Text("First Lesson Title") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = LightBorder
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = lessonTask,
            onValueChange = { lessonTask = it },
            label = { Text("Lesson Task Prompt") },
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = LightBorder
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotBlank()) {
                    onCreateCourse(
                        title,
                        description,
                        category,
                        price,
                        listOf(Triple(if (lessonTitle.isNotBlank()) lessonTitle else "Introduction", if (lessonTask.isNotBlank()) lessonTask else "Write code", "code"))
                    )
                    title = ""
                    description = ""
                    lessonTitle = ""
                    lessonTask = ""
                }
            },
            enabled = title.isNotBlank(),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
        ) {
            Text("Publish New Course", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AllCourseItems(
    allCourses: List<Course>,
    onDeleteCustomCourse: (Int) -> Unit
) {
    Column {
        allCourses.forEach { course ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .background(Color.White, RoundedCornerShape(4.dp))
                    .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = course.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = DarkCharcoal
                    )
                    Text(
                        text = "Instructor: ${course.instructor} • ${course.category}",
                        fontSize = 12.sp,
                        color = MutedText
                    )
                }

                if (course.isCustom) {
                    IconButton(onClick = { onDeleteCustomCourse(course.id) }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Delete course",
                            tint = Color(0xFFC62828)
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .background(Color(0xFFE7F4EC), RoundedCornerShape(2.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(
                            text = "Official",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryGreen
                        )
                    }
                }
            }
        }
    }
}
