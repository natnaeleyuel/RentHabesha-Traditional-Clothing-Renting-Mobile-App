package com.jct.renthabesha.core.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PolicySection(title: String, items: List<String>) {
    Text(
        title,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp)
    )
    items.forEach {
        Text(text = "- $it", fontSize = 14.sp)
    }
}