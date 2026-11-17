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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.data.Course
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen
import com.vidlearn.ui.components.SecondaryGreen
import com.vidlearn.ui.components.VidlearnLogo
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CertificateScreen(
    course: Course,
    userName: String?,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateString = SimpleDateFormat("MMMM dd, yyyy", Locale.getDefault()).format(Date())

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = DarkCharcoal
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = "Back to course", fontWeight = FontWeight.Bold, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(6.dp, PrimaryGreen, RoundedCornerShape(4.dp))
                .background(Color.White)
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                VidlearnLogo()

                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .height(4.dp)
                        .width(120.dp)
                        .background(SecondaryGreen)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Certificate of Completion",
                    color = MutedText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = course.title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                    color = DarkCharcoal,
                    lineHeight = 30.sp
                )

                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "awarded to",
                    color = MutedText,
                    fontSize = 13.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (!userName.isNullOrEmpty()) userName else "Learner",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryGreen
                )
                Box(
                    modifier = Modifier
                        .height(2.dp)
                        .width(180.dp)
                        .background(PrimaryGreen)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "for completing all ${course.lessons.size} videos and passing every practice sandbox task.",
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    color = DarkCharcoal
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Instructor: ${course.instructor}  ·  $dateString",
                    color = MutedText,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
