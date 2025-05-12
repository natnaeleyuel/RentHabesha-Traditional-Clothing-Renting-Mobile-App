package com.jct.renthabesha.core.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jct.renthabesha.R

@Composable
fun SettingItem(
    icon: ImageVector,
    title: String,
    showDivider: Boolean = true,
//    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /*navController.navigate() */ }
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, fontSize = 20.sp,modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.size(38.dp),
            painter = painterResource(id = R.drawable.forward_icon),
        contentDescription = "Go",
            tint = Color(0xFFb2c2bc)
        )
    }
}