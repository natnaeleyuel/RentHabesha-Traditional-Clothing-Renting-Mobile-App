
package com.example.projectmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectmanager.ui.theme.ProjectManagerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectManagerTheme {
                ResponsivePasswordManagerScreen()
            }
        }
    }
}

@Composable
fun ResponsivePasswordManagerScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val horizontalPadding = if (screenWidth < 360.dp) 16.dp else 24.dp

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }

    Scaffold (
        bottomBar = {
            ChangePasswordButton()
        }
    )
    { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopBar()

            Spacer(modifier = Modifier.height(screenHeight * 0.05f))

            PasswordSection(
                label = "Current Password",
                password = currentPassword,
                onPasswordChange = { currentPassword = it },
                showForgotPassword = true
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.04f))

            PasswordSection(
                label = "New Password",
                password = newPassword,
                onPasswordChange = { newPassword = it }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.04f))

            PasswordSection(
                label = "Confirm New Password",
                password = confirmNewPassword,
                onPasswordChange = { confirmNewPassword = it }
            )

            Spacer(modifier = Modifier.height(screenHeight * 0.20f))

        }
    }
}
@Composable
fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = { /* Handle back */ },
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFeaeaea), shape = CircleShape)
        ) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "Password Manager",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun PasswordSection(
    label: String,
    password: String,
    onPasswordChange: (String) -> Unit,
    showForgotPassword: Boolean = false
) {
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(12.dp))
        PasswordField(
            textValue = password,
            onValueChange = onPasswordChange
        )

        if (showForgotPassword) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Forgot Password?",
                color = Color(0xFF3B82F6),
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .align(Alignment.End)
                    .clickable { /* Forgot password logic */ }
            )
        }
    }
}

@Composable
fun PasswordField(
    textValue: String,
    onValueChange: (String) -> Unit
) {
    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = textValue,
        onValueChange = onValueChange,
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(40))
            .fillMaxWidth()
            .height(60.dp),
        visualTransformation = if (passwordVisible) VisualTransformation.None else AsteriskPasswordVisualTransformation(),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    painter = painterResource(
                        if (passwordVisible) R.drawable.baseline_visibility_off_24
                        else R.drawable.baseline_visibility_24
                    ),
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        }
    )
}

class AsteriskPasswordVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val out = AnnotatedString("*".repeat(text.text.length))
        return TransformedText(out, OffsetMapping.Identity)
    }
}

@Composable
fun ChangePasswordButton() {
    Box(
        modifier = Modifier
                .padding(vertical = 30.dp, horizontal = 20.dp)
    ) {
        Button(
            onClick = { /* Handle change password */ },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981)),
            shape = RoundedCornerShape(50),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)

        ) {
            Text(
                text = "Change",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

