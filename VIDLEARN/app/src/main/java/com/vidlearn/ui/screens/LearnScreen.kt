package com.vidlearn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.data.CheckResult
import com.vidlearn.data.Course
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.LightBorder
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen
import com.vidlearn.ui.components.SecondaryGreen
import com.vidlearn.ui.components.VideoPlayerView

@Composable
fun LearnScreen(
    course: Course,
    currentLessonIndex: Int,
    completedLessons: Set<String>,
    currentCode: String,
    onCodeChange: (String) -> Unit,
    checkResult: CheckResult?,
    onRunCheck: () -> Unit,
    onSelectLesson: (Int) -> Unit,
    isUnlocked: (Int) -> Boolean,
    onNextLesson: () -> Unit,
    onGetCertificate: () -> Unit,
    onBackClick: () -> Unit,
    learnTab: String,
    onTabSelect: (String) -> Unit,
    currentNote: String,
    onNoteChange: (String) -> Unit,
    videoSpeed: Float,
    onSpeedChange: (Float) -> Unit,
    onAddQnAQuestion: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val lesson = course.lessons.getOrNull(currentLessonIndex) ?: return
    val lessonKey = "${course.id}-$currentLessonIndex"
    val isCompleted = completedLessons.contains(lessonKey)
    val isLast = currentLessonIndex == course.lessons.size - 1
    val isCourseComplete = course.lessons.indices.all { completedLessons.contains("${course.id}-$it") }
    var newQuestionInput by remember { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        // Top Bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .background(DarkCharcoal)
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = course.title,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "${currentLessonIndex + 1}/${course.lessons.size}",
                color = Color(0xFF7BE0A8),
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // ExoPlayer Video Player
        VideoPlayerView(
            videoUri = lesson.videoUri,
            speed = videoSpeed,
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(Color.Black)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp)
        ) {
            Text(
                text = "${currentLessonIndex + 1}. ${lesson.title}",
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.weight(1f)
            )

            // Speed selection
            Icon(Icons.Filled.Speed, contentDescription = "Speed", tint = DarkCharcoal, modifier = Modifier.size(16.dp))
            Spacer(modifier = Modifier.width(4.dp))
            val speeds = listOf(0.75f, 1.0f, 1.25f, 1.5f, 2.0f)
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                items(speeds) { spd ->
                    val isSel = spd == videoSpeed
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(if (isSel) PrimaryGreen else Color(0xFFF7F9FA))
                            .border(1.dp, if (isSel) PrimaryGreen else LightBorder, RoundedCornerShape(4.dp))
                            .clickable { onSpeedChange(spd) }
                            .padding(horizontal = 6.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "${spd}x",
                            fontSize = 11.sp,
                            fontWeight = if (isSel) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSel) Color.White else DarkCharcoal
                        )
                    }
                }
            }
        }

        // Practice Sandbox Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
        ) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkCharcoal)
                        .padding(10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Terminal,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Practice sandbox · Lesson ${currentLessonIndex + 1}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE7F4EC))
                        .padding(12.dp)
                ) {
                    Text(
                        text = "Task: ${lesson.task}",
                        color = DarkCharcoal,
                        fontSize = 14.sp
                    )
                }

                OutlinedTextField(
                    value = currentCode,
                    onValueChange = onCodeChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp),
                    textStyle = TextStyle(
                        fontFamily = FontFamily.Monospace,
                        fontSize = 13.sp,
                        color = Color(0xFFE8E8F0)
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFF16161C),
                        unfocusedContainerColor = Color(0xFF16161C),
                        focusedBorderColor = PrimaryGreen,
                        unfocusedBorderColor = LightBorder
                    ),
                    shape = RoundedCornerShape(0.dp)
                )

                Box(modifier = Modifier.padding(10.dp)) {
                    Button(
                        onClick = onRunCheck,
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                        shape = RoundedCornerShape(2.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PlayArrow,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Run & check",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                if ((checkResult != null && checkResult.ok) || isCompleted) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                            .background(Color(0xFFD8F5E8), RoundedCornerShape(2.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "✓ Practice passed",
                            color = Color(0xFF0A5A38),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )

                        if (!isLast) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = onNextLesson,
                                colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Next video", color = Color.White, fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                            }
                        }

                        if (isLast && isCourseComplete) {
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = onGetCertificate,
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(Icons.Filled.WorkspacePremium, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Get certificate", color = Color.White, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                } else if (checkResult != null && !checkResult.ok) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .background(Color(0xFFFDE4E7), RoundedCornerShape(2.dp))
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "Not quite yet. Hint: ${checkResult.hint}",
                            color = Color(0xFFA3182B),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(18.dp))

        // Tabs: Course content, Q&A, Overview, Notes
        val tabs = listOf("Course content", "Q&A", "Overview", "Notes")
        val selectedTabIndex = tabs.indexOf(learnTab).coerceAtLeast(0)

        TabRow(
            selectedTabIndex = selectedTabIndex,
            containerColor = Color.White,
            contentColor = DarkCharcoal
        ) {
            tabs.forEach { tabName ->
                Tab(
                    selected = learnTab == tabName,
                    onClick = { onTabSelect(tabName) },
                    text = {
                        Text(
                            text = tabName,
                            fontWeight = if (learnTab == tabName) FontWeight.Bold else FontWeight.Normal,
                            color = if (learnTab == tabName) DarkCharcoal else MutedText,
                            fontSize = 12.sp
                        )
                    }
                )
            }
        }

        // Tab Content
        Box(modifier = Modifier.padding(14.dp)) {
            when (learnTab) {
                "Course content" -> {
                    Column {
                        Text(
                            text = "Course content",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        course.lessons.forEachIndexed { index, l ->
                            val key = "${course.id}-$index"
                            val done = completedLessons.contains(key)
                            val unlocked = isUnlocked(index)
                            val isCurrent = index == currentLessonIndex

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(if (isCurrent) Color(0xFFD9EFE2) else Color.White)
                                    .border(0.5.dp, LightBorder)
                                    .clickable(enabled = unlocked) { onSelectLesson(index) }
                                    .padding(14.dp)
                            ) {
                                Icon(
                                    imageVector = when {
                                        done -> Icons.Filled.CheckCircle
                                        unlocked -> Icons.Filled.PlayCircle
                                        else -> Icons.Filled.Lock
                                    },
                                    contentDescription = null,
                                    tint = when {
                                        done -> SecondaryGreen
                                        unlocked -> DarkCharcoal
                                        else -> MutedText
                                    },
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(10.dp))
                                Text(
                                    text = "${index + 1}. ${l.title}",
                                    color = if (unlocked) DarkCharcoal else MutedText,
                                    fontSize = 14.sp,
                                    modifier = Modifier.weight(1f)
                                )
                                Text(
                                    text = l.duration,
                                    fontSize = 12.sp,
                                    color = MutedText
                                )
                            }
                        }
                    }
                }
                "Q&A" -> {
                    Column {
                        Text(
                            text = "Questions & Answers",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        course.qnaItems.forEach { qna ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 10.dp)
                                    .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                                    .background(Color.White)
                                    .padding(12.dp)
                            ) {
                                Column {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(text = qna.author, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                        Spacer(modifier = Modifier.weight(1f))
                                        Text(text = qna.date, fontSize = 11.sp, color = MutedText)
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(text = qna.question, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = DarkCharcoal)

                                    qna.answers.forEach { ans ->
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color(0xFFE7F4EC), RoundedCornerShape(2.dp))
                                                .padding(8.dp)
                                        ) {
                                            Text(text = ans, fontSize = 12.sp, color = PrimaryGreen)
                                        }
                                    }
                                }
                            }
                        }

                        if (course.qnaItems.isEmpty()) {
                            Text(text = "No questions asked yet. Be the first to ask!", fontSize = 13.sp, color = MutedText, modifier = Modifier.padding(bottom = 12.dp))
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            OutlinedTextField(
                                value = newQuestionInput,
                                onValueChange = { newQuestionInput = it },
                                placeholder = { Text("Ask a question...") },
                                modifier = Modifier.weight(1f),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryGreen, unfocusedBorderColor = LightBorder)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(
                                onClick = {
                                    if (newQuestionInput.isNotBlank()) {
                                        onAddQnAQuestion(newQuestionInput)
                                        newQuestionInput = ""
                                    }
                                }
                            ) {
                                Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = PrimaryGreen)
                            }
                        }
                    }
                }
                "Overview" -> {
                    Column {
                        Text(
                            text = "About this course",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(text = course.description, fontSize = 14.sp, color = DarkCharcoal)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "By ${course.instructor} (${course.instructorTitle}) · ${course.rating} rating",
                            fontSize = 13.sp,
                            color = MutedText
                        )
                    }
                }
                "Notes" -> {
                    Column {
                        Text(
                            text = "Personal Notes",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        OutlinedTextField(
                            value = currentNote,
                            onValueChange = onNoteChange,
                            placeholder = { Text("Type your notes for this lesson here...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(130.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryGreen,
                                unfocusedBorderColor = LightBorder
                            )
                        )
                    }
                }
            }
        }
    }
}
