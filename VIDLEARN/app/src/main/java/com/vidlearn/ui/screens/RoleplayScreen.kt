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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.data.PracticeScenario
import com.vidlearn.data.RoleMessage
import com.vidlearn.data.SampleData
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.LightBorder
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen

@Composable
fun RoleplayScreen(
    selectedScenario: PracticeScenario?,
    roleMessages: List<RoleMessage>,
    roleInput: String,
    onRoleInputChange: (String) -> Unit,
    onSendReply: (String) -> Unit,
    onSelectScenario: (PracticeScenario) -> Unit,
    onResetScenario: () -> Unit,
    onExitScenario: () -> Unit,
    roleTurn: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        if (selectedScenario == null) {
            // Scenario Selection List
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clickable { onExitScenario() }
                    .padding(bottom = 12.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null,
                    tint = DarkCharcoal,
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(text = "Back to courses", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE7F4EC), RoundedCornerShape(4.dp))
                    .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                    .padding(16.dp)
            ) {
                Column {
                    Text(
                        text = "GUIDED PRACTICE COACH",
                        color = PrimaryGreen,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Practice the conversation before it counts.",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkCharcoal
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Choose a realistic scenario, write your response, and get immediate coaching on clarity, empathy, and specific next steps.",
                        color = MutedText,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Choose a practice scenario",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Each practice takes a few turns and can be repeated.",
                color = MutedText,
                fontSize = 13.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                items(SampleData.scenarios) { scenario ->
                    val colorHex = try {
                        Color(android.graphics.Color.parseColor(scenario.color))
                    } catch (e: Exception) {
                        PrimaryGreen
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                            .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
                            .background(Color.White)
                            .clickable { onSelectScenario(scenario) }
                            .padding(16.dp)
                    ) {
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(CircleShape)
                                        .background(colorHex)
                                ) {
                                    Icon(
                                        imageVector = Icons.Filled.Psychology,
                                        contentDescription = null,
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = scenario.title,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = DarkCharcoal
                                    )
                                    Text(
                                        text = "${scenario.topic} · ${scenario.level}",
                                        fontSize = 12.sp,
                                        color = MutedText
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = "Practice a realistic conversation with ${scenario.persona}.",
                                color = MutedText,
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = "Start practice  →",
                                color = PrimaryGreen,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                    }
                }
            }
        } else {
            // Chat Room for Active Scenario
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .border(1.dp, LightBorder, RoundedCornerShape(4.dp))
            ) {
                // Header
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(DarkCharcoal)
                        .padding(12.dp)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(PrimaryGreen)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Psychology,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = selectedScenario.persona,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                        Text(
                            text = selectedScenario.title,
                            color = Color(0xFFC9E6D5),
                            fontSize = 11.sp
                        )
                    }
                    IconButton(onClick = onResetScenario) {
                        Icon(Icons.Filled.Refresh, contentDescription = "Restart", tint = Color.White)
                    }
                    IconButton(onClick = onExitScenario) {
                        Icon(Icons.Filled.Close, contentDescription = "Exit", tint = Color.White)
                    }
                }

                // Messages Container
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .background(Color(0xFFF7F9FA))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(roleMessages) { msg ->
                        val isLearner = msg.from == "learner"
                        val isFeedback = msg.from == "feedback"

                        Box(
                            modifier = Modifier
                                .fillMaxWidth(),
                            contentAlignment = if (isLearner) Alignment.CenterEnd else Alignment.CenterStart
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(
                                        when {
                                            isLearner -> PrimaryGreen
                                            isFeedback -> Color(0xFFEDF6F0)
                                            else -> Color.White
                                        }
                                    )
                                    .border(
                                        1.dp,
                                        if (isLearner) PrimaryGreen else LightBorder,
                                        RoundedCornerShape(8.dp)
                                    )
                                    .padding(12.dp)
                            ) {
                                Column {
                                    if (isFeedback) {
                                        Text(
                                            text = "COACHING FEEDBACK",
                                            color = PrimaryGreen,
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 11.sp,
                                            modifier = Modifier.padding(bottom = 4.dp)
                                        )
                                    }
                                    Text(
                                        text = msg.text,
                                        color = if (isLearner) Color.White else DarkCharcoal,
                                        fontSize = 14.sp,
                                        lineHeight = 19.sp
                                    )
                                }
                            }
                        }
                    }
                }

                // Completion status or Input Box
                if (roleTurn >= selectedScenario.turnCount) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE7F4EC))
                            .padding(12.dp)
                    ) {
                        Text(
                            text = "Practice complete. Review the coaching feedback above or tap restart to try again.",
                            color = PrimaryGreen,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                } else {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .padding(8.dp)
                    ) {
                        OutlinedTextField(
                            value = roleInput,
                            onValueChange = onRoleInputChange,
                            placeholder = { Text("Write your response...") },
                            modifier = Modifier.weight(1f),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryGreen,
                                unfocusedBorderColor = LightBorder
                            )
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = { onSendReply(roleInput) },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                            shape = RoundedCornerShape(4.dp),
                            enabled = roleInput.isNotBlank()
                        ) {
                            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = Color.White)
                        }
                    }
                }
            }
        }
    }
}
