package com.jct.renthabesha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.jct.renthabesha.core.domain.models.ClothingItem
import com.jct.renthabesha.core.domain.models.NotificationItem
import com.jct.renthabesha.core.presentation.components.BottomNavigationBar
import com.jct.renthabesha.core.presentation.navigation.AppNavigation
import com.jct.renthabesha.core.presentation.navigation.Screen
import com.jct.renthabesha.core.presentation.screens.AddClothingScreen
import com.jct.renthabesha.core.presentation.screens.AddLocationScreen
import com.jct.renthabesha.core.presentation.screens.EditClothingScreen
import com.jct.renthabesha.core.presentation.screens.HomeScreen
import com.jct.renthabesha.core.presentation.screens.NotificationScreen
import com.jct.renthabesha.core.presentation.screens.ProductDetailScreen
import com.jct.renthabesha.core.presentation.screens.ProfileScreen
import com.jct.renthabesha.core.presentation.screens.SettingsScreen
import com.jct.renthabesha.core.presentation.screens.SplashScreen
import com.jct.renthabesha.core.presentation.screens.WelcomeScreen
import com.jct.renthabesha.core.presentation.theme.RentHabeshaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RentHabeshaTheme {

//                val sampleNotifications = listOf(
//                    NotificationItem(
//                        id = 1,
//                        title = "Order Shipped!",
//                        message = "Your Ethiopian suit is on its way! Thank you for choosing RentHabesha!",
//                        isNew = true
//                    ),
//                    NotificationItem(
//                        id = 2,
//                        title = "Renting Confirmed!",
//                        message = "Your Ethiopian suit is reserved! Thank you for choosing RentHabesha!",
//                        isNew = false
//                    ),
//                    NotificationItem(
//                        id = 3,
//                        title = "Near Arrival",
//                        message = "A stunning Habesha kernis just listed near you! Rent now before it's gone.",
//                        isNew = true
//                    ),
//                    NotificationItem(
//                        id = 4,
//                        title = "Payment Method Added",
//                        message = "A new credit card was successfully added to your account. Manage payment methods in your profile.",
//                        isNew = false
//                    ),
//                    NotificationItem(
//                        id = 5,
//                        title = "Let's Get Started!",
//                        message = "Your perfect Ethiopian outfit is waiting! Browse our collection now and rent your first traditional look with ease. #RentInStyle",
//                        isNew = false
//                    )
//                )
//
//                MaterialTheme {
//                    NotificationScreen(
//                        notifications = sampleNotifications,
//                        onMarkAllAsRead = { /* Handle mark all as read */ },
//                        onNotificationClick = { /* Handle notification click */ }
//                    )
//                }

//                AppNavigation()
                AddClothingScreen(navController = rememberNavController())
//                ProductDetailScreen(
//                    product = ClothingItem(
//                        title = "Ethiopian Suit",
//                        category = "Men's Style",
//                        description = "The Ethiopian Men's Traditional Suit is a classic and elegant formal wear that showcases rich cultural heritage.",
//                        price = 1000,
//                        imageUrl = "this one",
//                        dateAdded = "2024-07-01".toLong(),
//                        rentalPeriod = "1 - 7 days"
//                    ),
//                    onAddToCart = { quantity -> println("Added $quantity to cart") }
//                )

            }
        }
    }
}