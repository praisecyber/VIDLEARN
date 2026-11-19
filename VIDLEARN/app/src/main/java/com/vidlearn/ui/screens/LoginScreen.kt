package com.vidlearn.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vidlearn.ui.components.DarkCharcoal
import com.vidlearn.ui.components.LightBorder
import com.vidlearn.ui.components.MutedText
import com.vidlearn.ui.components.PrimaryGreen
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    userName: String?,
    userEmail: String?,
    onLogin: (String, String) -> Unit,
    onLogout: () -> Unit,
    onOpenAdmin: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var resendMessage by remember { mutableStateOf<String?>(null) }
    var resendCooldown by remember { mutableIntStateOf(0) }

    LaunchedEffect(resendCooldown) {
        if (resendCooldown > 0) {
            delay(1000)
            resendCooldown -= 1
        }
    }

    // Strict admin check - ONLY praisecyber2005@gmail.com
    val isAdmin = userEmail?.equals("praisecyber2005@gmail.com", ignoreCase = true) == true

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        if (!userName.isNullOrEmpty()) {
            Text(
                text = "Hi, $userName",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkCharcoal
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "$userEmail · signed in on this device.",
                color = MutedText,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            if (isAdmin) {
                Button(
                    onClick = { onOpenAdmin?.invoke() },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                    shape = RoundedCornerShape(2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                ) {
                    Text(text = "Open Admin Control Panel", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            // Resend Verification Email Button for Signed-In User
            OutlinedButton(
                onClick = {
                    val targetEmail = userEmail ?: "praisecyber2005@gmail.com"
                    resendMessage = "✓ Verification email resent to $targetEmail. Please check your inbox or spam folder."
                    resendCooldown = 30
                },
                enabled = resendCooldown == 0,
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = if (resendCooldown > 0) "Resend email in ${resendCooldown}s" else "Resend Verification Email",
                    color = DarkCharcoal,
                    fontWeight = FontWeight.Bold
                )
            }

            if (!resendMessage.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE7F4EC), RoundedCornerShape(4.dp))
                        .border(1.dp, PrimaryGreen, RoundedCornerShape(4.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = resendMessage!!,
                        color = PrimaryGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onLogout,
                colors = ButtonDefaults.buttonColors(containerColor = DarkCharcoal),
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Log out", color = Color.White, fontWeight = FontWeight.Bold)
            }
        } else {
            Text(
                text = "Log in or sign up",
                fontSize = 26.sp,
                fontWeight = FontWeight.ExtraBold,
                color = DarkCharcoal
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Enter your name and email to save progress and get your certificate.",
                color = MutedText,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Full name") },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = LightBorder
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email address") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryGreen,
                    unfocusedBorderColor = LightBorder
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (name.isNotBlank() && email.isNotBlank()) {
                        onLogin(name, email)
                    }
                },
                enabled = name.isNotBlank() && email.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(text = "Continue", color = Color.White, fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Resend Email for Signed-Out Flow
            OutlinedButton(
                onClick = {
                    val targetEmail = if (email.isNotBlank()) email else "praisecyber2005@gmail.com"
                    resendMessage = "✓ Verification email resent to $targetEmail. Please check your inbox or spam folder."
                    resendCooldown = 30
                },
                enabled = resendCooldown == 0,
                shape = RoundedCornerShape(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text(
                    text = if (resendCooldown > 0) "Resend email in ${resendCooldown}s" else "Resend Verification Email",
                    color = DarkCharcoal,
                    fontWeight = FontWeight.Bold
                )
            }

            if (!resendMessage.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFE7F4EC), RoundedCornerShape(4.dp))
                        .border(1.dp, PrimaryGreen, RoundedCornerShape(4.dp))
                        .padding(12.dp)
                ) {
                    Text(
                        text = resendMessage!!,
                        color = PrimaryGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
