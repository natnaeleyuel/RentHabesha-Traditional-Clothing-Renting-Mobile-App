package com.jct.renthabesha.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jct.renthabesha.R

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
                BorderStroke(1.dp, Color(0xFF00A86B)),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        PaymentOptionRow(
            iconRes = R.drawable.stripe_icon,
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
            iconRes = R.drawable.paypal_icon,
            title = "Paypal",
            isSelected = selectedOption == "Paypal",
            onClick = { selectedOption = "Paypal" }
        )
    }
}
