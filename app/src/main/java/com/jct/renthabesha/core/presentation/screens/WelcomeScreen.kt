package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.ButtonCompos
import com.jct.renthabesha.core.presentation.navigation.Screen
import com.jct.renthabesha.core.presentation.theme.Poppins
import com.jct.renthabesha.core.presentation.theme.TextStrong
import android.util.Log

@Composable
fun WelcomeScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(95.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .padding(start = 20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_six),
                contentDescription = "Dress Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(1f)
                    .height(280.dp)
                    .clip(RoundedCornerShape(40.dp))
                    .border(
                        BorderStroke(1.dp, Color(0xFFB88D8D)),
                        shape = RoundedCornerShape(40.dp)
                    )
            )

            Spacer(modifier = Modifier.width(30.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.img_one),
                    contentDescription = "Female Model",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(133.dp)
                        .width(110.dp)
                        .clip(RoundedCornerShape(59.dp))
                )
                Image(
                    painter = painterResource(id = R.drawable.img_nineteen),
                    contentDescription = "Male Model",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(133.dp)
                        .width(110.dp)
                        .clip(RoundedCornerShape(59.dp))
                )
            }
        }

        Spacer(modifier = Modifier.height(55.dp))

        Text(
            text = "Welcome to Rent Habesha!",
            style = MaterialTheme.typography.titleMedium.copy(
                color = if (isSystemInDarkTheme()) Color.White else TextStrong,
                fontFamily = Poppins
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Discover. Rent. Celebrate.",
            style = MaterialTheme.typography.bodyLarge.copy(
                color = if (isSystemInDarkTheme()) Color.White else TextStrong,
                fontFamily = Poppins
            ),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your go-to platform for renting stunning traditional Ethiopian clothing for any occasion.",
            color = Color.Gray,
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
                fontWeight = MaterialTheme.typography.bodySmall.fontWeight,
                fontFamily = Poppins,
            ),
        )

        Spacer(modifier = Modifier.height(50.dp))

        ButtonCompos(
            onClicked = {Log.d("NAV_DEBUG", "Attempting to navigate to Signup")
                try {
                    navController.navigate(Screen.Signup.route)
                    Log.d("NAV_DEBUG", "Navigation to Signup initiated")
                } catch (e: Exception) {
                    Log.e("NAV_ERROR", "Failed to navigate to Signup", e)
            }},
            text = R.string.get_started,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account? ",
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = if (isSystemInDarkTheme()) Color.White else TextStrong,
                    fontFamily = Poppins
                ),
            )
            Text(
                modifier = Modifier
                    .clickable {
                    navController.navigate(Screen.Signin.route)
                },
                text = "Sign in",
                color = Color(0xFF00A86B),
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = TextDecoration.Underline
            )
        }
    }
}
