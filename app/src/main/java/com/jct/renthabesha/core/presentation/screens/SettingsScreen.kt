package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.SettingItem
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.BottomNavigationBar
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.navigation.Screen

@Composable
fun SettingsScreen(
    navController: NavController
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val horizontalPadding = if (screenWidth < 360.dp) 16.dp else 24.dp
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp, horizontal = horizontalPadding)
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            TopNavigationBar(title = "Settings", onClick = { navController.navigate(Screen.Profile.route) })

            Spacer(modifier = Modifier.height(24.dp))

            SettingItem(
                ImageVector.vectorResource(id = R.drawable.notification_icon),
                "NotificationScreen"
            )
            SettingItem(ImageVector.vectorResource(id = R.drawable.lock_icon), "Password Manager")
            SettingItem(
                ImageVector.vectorResource(id = R.drawable.delete_account_icon),
                "Delete Account"
            )
        }
    }
}
