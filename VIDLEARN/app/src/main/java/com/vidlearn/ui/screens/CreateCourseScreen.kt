package com.vidlearn.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.LightBorder
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen

@Composable
fun CreateCourseScreen(
    userName: String?,
    onLoginClick: () -> Unit,
    onCreateCourse: (String, String, String, String, List<Triple<String, String, String>>) -> Unit,
    modifier: Modifier = Modifier
) {
    if (userName.isNullOrEmpty()) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Text(
                text = "Log in to create a course",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = DarkCharcoal
            )
            Spacer(modifier = Modifier.height(14.dp))
            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Log in", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
        return
    }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var price by remember { mutableStateOf("₦5,000") }
    var category by remember { mutableStateOf("Business") }

    val rows = remember {
        mutableStateListOf(
            Triple("", "", ""),
            Triple("", "", ""),
            Triple("", "", "")
        )
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Create a course",
            fontSize = 26.sp,
            fontWeight = FontWeight.ExtraBold,
            color = DarkCharcoal
        )
        Text(
            text = "Add lessons with a practice task. Learners must write your required word in the sandbox to unlock the next video.",
            color = MutedText,
            fontSize = 14.sp,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Course title") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = LightBorder
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(10.dp))

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

        Spacer(modifier = Modifier.height(10.dp))

        OutlinedTextField(
            value = price,
            onValueChange = { price = it },
            label = { Text("Price") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = PrimaryGreen,
                unfocusedBorderColor = LightBorder
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Lessons", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(8.dp))

        rows.indices.forEach { i ->
            Column(modifier = Modifier.padding(bottom = 12.dp)) {
                Text(text = "Lesson ${i + 1}", fontSize = 14.sp, fontWeight = FontWeight.SemiBold, color = DarkCharcoal)
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = rows[i].first,
                    onValueChange = { rows[i] = Triple(it, rows[i].second, rows[i].third) },
                    placeholder = { Text("Lesson title") },
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryGreen, unfocusedBorderColor = LightBorder),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = rows[i].second,
                    onValueChange = { rows[i] = Triple(rows[i].first, it, rows[i].third) },
                    placeholder = { Text("Practice task prompt") },
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryGreen, unfocusedBorderColor = LightBorder),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = rows[i].third,
                    onValueChange = { rows[i] = Triple(rows[i].first, rows[i].second, it) },
                    placeholder = { Text("Required word to pass") },
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = PrimaryGreen, unfocusedBorderColor = LightBorder),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                onCreateCourse(title, description, category, price, rows.toList())
            },
            enabled = title.isNotBlank() && rows.any { it.first.isNotBlank() },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
            shape = RoundedCornerShape(2.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(text = "Publish course", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
