package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.BottomNavigationBar
import com.jct.renthabesha.core.presentation.components.ProfileItem
import com.jct.renthabesha.core.presentation.components.ProfilePicture
import com.jct.renthabesha.core.presentation.navigation.Screen

@Composable
fun ProfileScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ){ innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopNavigationBar(title = "Profile", onClick = { navController.navigate(Screen.Home.route) })

            Spacer(modifier = Modifier.height(16.dp))

//            ProfilePicture(
//                initialImageRes = R.drawable.image_icon,
//            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileItem(ImageVector.vectorResource(id = R.drawable.clock_rotate_left_icon), "Renting History")
            ProfileItem(ImageVector.vectorResource(id = R.drawable.credit_card_icon), "Payment Methods")
            ProfileItem(ImageVector.vectorResource(id = R.drawable.settings_icon), "Settings")
            ProfileItem(ImageVector.vectorResource(id = R.drawable.privacy_icon), "Privacy Policy")
            ProfileItem(ImageVector.vectorResource(id = R.drawable.logout_icon), "Log Out", showDivider = false)
        }
    }
}
