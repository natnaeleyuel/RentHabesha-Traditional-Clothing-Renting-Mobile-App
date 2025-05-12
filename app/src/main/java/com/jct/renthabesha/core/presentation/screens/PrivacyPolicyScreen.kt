package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.BottomNavigationBar
import com.jct.renthabesha.core.presentation.components.PolicySection
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.navigation.Screen

@Composable
fun PrivacyPolicyScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            TopNavigationBar(title = "Privacy Policy", onClick = { navController.navigate(Screen.Settings.route) })
            Text(
                "RentHabesha Privacy Policy",
                color = Color(0xFF00B686),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            PolicySection(
                title = "We Collect:",
                items = listOf(
                    "Basic info (name, email, phone)",
                    "Payment details (processed securely)",
                    "Rental history & preferences",
                    "Device data for app improvement"
                )
            )

            PolicySection(
                title = "We Use It To:",
                items = listOf(
                    "Process rentals & deliveries",
                    "Improve our app",
                    "Send important updates"
                )
            )

            PolicySection(
                title = "We Share Only When Needed:",
                items = listOf(
                    "With trusted service providers",
                    "For legal compliance"
                )
            )

            PolicySection(
                title = "Your Rights:",
                items = listOf(
                    "Access/delete your data",
                    "Opt out of marketing"
                )
            )

            Text(
                buildAnnotatedString {
                    append("Security: ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Normal)) {
                        append("We protect your data with encryption.")
                    }
                },
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
