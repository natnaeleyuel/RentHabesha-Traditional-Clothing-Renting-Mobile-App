package com.example.settings

import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.settings.ui.theme.SettingsTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SettingsTheme {
                MyProfileScreen()
            }
        }
    }
}

@Composable
fun MyProfileScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val horizontalPadding = if (screenWidth < 360.dp) 16.dp else 24.dp
    var selectedTab by remember { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = { tabIndex ->
                    selectedTab = tabIndex
                }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(vertical = 20.dp, horizontal = horizontalPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
//            when (selectedTab) {
//                0 -> HomeScreen()
//                1 -> OrdersScreen()
//                2 -> ProfileScreen()
//            }
//            TopBar()
            Spacer(modifier = Modifier.height(16.dp))

            Spacer(modifier = Modifier.height(24.dp))

            SettingItem(ImageVector.vectorResource(id = R.drawable.baseline_notifications_24), "Notification Settings")
            SettingItem(ImageVector.vectorResource(id = R.drawable.baseline_lock_24), "Password Manager")
            SettingItem(ImageVector.vectorResource(id = R.drawable.baseline_person_remove_24), "Delete Account")
        }
    }
}

@Composable
fun SettingItem(icon: ImageVector, title: String, showDivider: Boolean = true) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ }
            .padding(vertical = 16.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = title)
        Spacer(modifier = Modifier.width(16.dp))
        Text(title, fontSize = 20.sp,modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.size(38.dp),
            painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "Go",
            tint = Color(0xFFb2c2bc)

        )
    }
}

@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 30.dp, horizontal = 30.dp)
            .border(2.dp, Color(0xFF19b579), RoundedCornerShape(50))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
//        NavigationIcon(
//            icon = Icons.Default.Home,
//            description = "Home",
//            isSelected = selectedTab == 0,
//            onClick = { onTabSelected(0) }
//        )
//        NavigationIcon(
//            icon = ImageVector.vectorResource(id = R.drawable.baseline_lock_24),
//            description = "Orders",
//            isSelected = selectedTab == 1,
//            onClick = { onTabSelected(1) }
//        )
//        NavigationIcon(
//            icon = Icons.Default.AccountCircle,
//            description = "Profile",
//            isSelected = selectedTab == 2,
//            onClick = { onTabSelected(2) }
//        )
    }
}

//@Composable
//fun NavigationIcon(icon: ImageVector, description: String, isSelected: Boolean, onClick: () -> Unit) {
//    Box(
//        modifier = Modifier
//            .size(50.dp)
//            .background(
//                color = if (isSelected) Color(0xFF19b579) else Color.Transparent,
//                shape = CircleShape
//            ),
//        contentAlignment = Alignment.Center
//    ) {
//        IconButton(onClick = onClick) {
//            Icon(
//                imageVector = icon,
//                contentDescription = description,
//            )
//        }
//    }
//}
//
//@Composable
//fun HomeScreen() {
////    Text(text = "This is the Home Screen", style = MaterialTheme.typography.headlineSmall)
//}
//
//@Composable
//fun OrdersScreen() {
////    Text(text = "", style = MaterialTheme.typography.headlineSmall)
//}
//
//@Composable
//fun ProfileScreen() {
////    Text(text = "This is the Profile Screen", style = MaterialTheme.typography.headlineSmall)
//}
