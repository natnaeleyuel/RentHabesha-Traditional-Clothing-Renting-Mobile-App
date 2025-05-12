package com.example.profile

import android.icu.text.ListFormatter.Width
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.Bitmap
import android.os.Build
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*

import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.graphics.ImageDecoder
import android.provider.MediaStore
import com.example.profile.ui.theme.ProfileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfileTheme {
                MyProfileScreen()
            }
        }
    }
}
@Composable
fun MyProfileScreen() {
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
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> OrdersScreen()
                2 -> ProfileScreen()
            }

            TopBar()
            Spacer(modifier = Modifier.height(16.dp))

            // Profile Image Placeholder
            ProfilePicture(
                initialImageRes = R.drawable.ic_launcher_foreground,
                isOnline = true,  // or false based on your logic
            )

            Spacer(modifier = Modifier.height(24.dp))

            ProfileItem(ImageVector.vectorResource(id = R.drawable.baseline_history_24), "Renting History")
            ProfileItem(ImageVector.vectorResource(id = R.drawable.baseline_chevron_right_24), "Payment Methods")
            ProfileItem(ImageVector.vectorResource(id = R.drawable.baseline_privacy_tip_24), "Settings")
            ProfileItem(ImageVector.vectorResource(id = R.drawable.ic_launcher_foreground), "Privacy Policy")
            ProfileItem(ImageVector.vectorResource(id = R.drawable.baseline_chevron_right_24), "Log Out", showDivider = false)
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
            Icon(
                Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back",
            )
        }

        Spacer(modifier = Modifier.weight(0.7f))

        Text(
            text = "Profile",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}
@Composable
fun ProfilePicture(
    initialImageRes: Int,
    isOnline: Boolean
) {
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    val bitmap by remember { mutableStateOf<Bitmap?>(null) }
    var isEditingName by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf(TextFieldValue("Samrawit Seyum")) }
    val context = LocalContext.current
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        profileImageUri = uri
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp)
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .aspectRatio(1f)
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .border(1.dp, Color.Black, CircleShape)
                    .clickable {
                        // Launch file picker when image is clicked
                        imagePickerLauncher.launch("image/*")
                    },
                shape = CircleShape,
                color = Color.White
            ) {
                if (bitmap != null) {
                    // Show the picked image
                    Image(
                        bitmap = bitmap!!.asImageBitmap(),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    // Show default image
                    Image(
                        painter = painterResource(id = initialImageRes),
                        contentDescription = "Profile Picture",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
            // Status indicator
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.BottomEnd)
                    .border(0.5.dp, Color.Black, CircleShape)
                    .background(if (isOnline) Color(0xFF19b579) else Color.Gray, CircleShape)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        if (isEditingName) {
            TextField(
                value = name,
                onValueChange = { name = it },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { isEditingName = false }) {
                Text("Save")
            }
        } else {
            Text(
                text = name.text,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    isEditingName = true // Start editing when clicked
                }
            )
        }
    }
}
@Composable
fun ProfileItem(icon: ImageVector, title: String, showDivider: Boolean = true) {
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
            painter = painterResource(id = R.drawable.baseline_chevron_right_24), contentDescription = "Go",
            tint = Color(0xFFb2c2bc)
        )
    }
    if (showDivider) {
        HorizontalDivider(
            color = Color(0xFFA49C9C),
            modifier = Modifier.fillMaxWidth()
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
        NavigationIcon(
            icon = Icons.Default.Home,
            description = "Home",
            isSelected = selectedTab == 0,
            onClick = { onTabSelected(0) }
        )
        NavigationIcon(
            icon = ImageVector.vectorResource(id = R.drawable.baseline_history_24),
            description = "Orders",
            isSelected = selectedTab == 1,
            onClick = { onTabSelected(1) }
        )
        NavigationIcon(
            icon = Icons.Default.AccountCircle,
            description = "Profile",
            isSelected = selectedTab == 2,
            onClick = { onTabSelected(2) }
        )
    }
}

@Composable
fun NavigationIcon(icon: ImageVector, description: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .background(
                color = if (isSelected) Color(0xFF19b579) else Color.Transparent,
                shape = CircleShape
            ),
        contentAlignment = Alignment.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = description,
            )
        }
    }
}

@Composable
fun HomeScreen() {
//    Text(text = "This is the Home Screen", style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun OrdersScreen() {
//    Text(text = "", style = MaterialTheme.typography.headlineSmall)
}

@Composable
fun ProfileScreen() {
//    Text(text = "This is the Profile Screen", style = MaterialTheme.typography.headlineSmall)
}


