package com.example.paymentmethods

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.paymentmethods.ui.theme.PaymentMethodsTheme
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.ui.text.style.TextAlign

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PaymentMethodsTheme {
                PaymentMethodScreen()
            }
        }
    }
}

@Composable
fun PaymentMethodScreen() {
    var selectedOption by remember { mutableStateOf<String?>(null) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val horizontalPadding = if (screenWidth < 360.dp) 16.dp else 24.dp

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
                .padding(vertical = 30.dp, horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
        // Back button and title
            TopBar(
                text =  "Payment Methods",
                onClick = { /* handle back press */ }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Credit & Debit Card section
            Text(
                text = "Credit & Debit Card",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Start

            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .border(1.dp, Color(0xFF00A86B), RoundedCornerShape(8.dp))
                    .clickable { /* handle add card */ }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_credit_card_24), // replace with your card icon
                    contentDescription = "Add Card",
                    tint = Color(0xFF00A86B),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Add Card",
                    modifier = Modifier.weight(1f),
                    fontSize = 16.sp
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_chevron_right_24), // replace with your chevron icon
                    contentDescription = "Go",
                    tint = Color.Gray,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // More Payment Options section

            PaymentOptionsCard()
            Spacer(modifier = Modifier.weight(1f))

        }
    }
}

@Composable
fun TopBar(
    text: String,
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFFeaeaea), shape = CircleShape)
        ) {
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back",
            )
        }

        Spacer(modifier = Modifier.weight(0.7f))

        Text(
            text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.weight(1f))
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
                text = "Confirm Payment",
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
@Composable
fun PaymentOptionsCard() {
    var selectedOption by remember { mutableStateOf<String?>(null) }

    Text(
        text = "More Payment Options",
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium,
        modifier = Modifier
            .padding(bottom = 8.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Start
    )
    Spacer(modifier = Modifier.height(10.dp))

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(1.dp, Color(0xFF00A86B)), // Green border
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        PaymentOptionRow(
            iconRes = R.drawable.baseline_credit_card_24, // replace with your Stripe icon
            title = "Stripe",
            isSelected = selectedOption == "Stripe",
            onClick = { selectedOption = "Stripe" }
        )

        // Divider between options
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .padding(horizontal = 8.dp)
                .border(BorderStroke(0.5.dp, Color(0xFF00A86B)))
        )

        PaymentOptionRow(
            iconRes = R.drawable.baseline_strikethrough_s_24, // replace with your PayPal icon
            title = "Paypal",
            isSelected = selectedOption == "Paypal",
            onClick = { selectedOption = "Paypal" }
        )
    }
}

@Composable
fun PaymentOptionRow(
    iconRes: Int,
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(14.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconRes),
            contentDescription = title,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        RadioButton(
            selected = isSelected,
            onClick = { onClick() },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF00A86B)
            )
        )
    }
}
