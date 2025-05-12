package com.jct.renthabesha.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.*
import androidx.navigation.createGraph
import com.jct.renthabesha.core.presentation.screens.AddCardScreen
import com.jct.renthabesha.core.presentation.screens.AddClothingScreen
import com.jct.renthabesha.core.presentation.screens.AddLocationScreen
import com.jct.renthabesha.core.presentation.screens.AdminDashboardScreen
import com.jct.renthabesha.core.presentation.screens.CheckoutScreen
import com.jct.renthabesha.core.presentation.screens.ClothingManagementScreen
import com.jct.renthabesha.core.presentation.screens.CompleteProfileScreen
import com.jct.renthabesha.core.presentation.screens.EditClothingScreen
import com.jct.renthabesha.core.presentation.screens.HomeScreen
import com.jct.renthabesha.core.presentation.screens.SignupScreen
import com.jct.renthabesha.core.presentation.screens.SplashScreen
import com.jct.renthabesha.core.presentation.screens.WelcomeScreen
import com.jct.renthabesha.core.presentation.screens.LoginScreen
import com.jct.renthabesha.core.presentation.screens.MyCartScreen
import com.jct.renthabesha.core.presentation.screens.MyOrdersScreen
import com.jct.renthabesha.core.presentation.screens.NotificationScreen
import com.jct.renthabesha.core.presentation.screens.PasswordManagerScreen
import com.jct.renthabesha.core.presentation.screens.PaymentMethodScreen
import com.jct.renthabesha.core.presentation.screens.PaymentStatusScreen
import com.jct.renthabesha.core.presentation.screens.PrivacyPolicyScreen
import com.jct.renthabesha.core.presentation.screens.ProductDetailScreen
import com.jct.renthabesha.core.presentation.screens.ProfileScreen
import com.jct.renthabesha.core.presentation.screens.SearchScreen
import com.jct.renthabesha.core.presentation.screens.SettingsScreen
import com.jct.renthabesha.core.presentation.screens.ShippingAddressScreen
import com.jct.renthabesha.core.presentation.screens.UserManagementScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val graph =
        navController.createGraph(startDestination = Screen.Splash.route) {
            composable(route = Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(route = Screen.Profile.route) {
                ProfileScreen(navController)
            }
            composable(route = Screen.Orders.route) {
                MyOrdersScreen(navController)
            }
            composable(route = Screen.AddCard.route) {
                AddCardScreen(
                    navController = navController,
                    onSaveCard = TODO(),
                    modifier = Modifier,
                )
            }
            composable(route = Screen.Signup.route){
                SignupFlow(mainNavController = navController)
            }
            composable(route = Screen.AddClothing.route) {
                AddClothingScreen(navController)
            }
            composable(route = Screen.AdminDashboard.route) {
                AdminDashboardScreen(navController)
            }
            composable(route = Screen.Checkout.route) {
                CheckoutScreen(
                    navController,
                    shippingAddress = TODO(),
                    orderItems = TODO(),
                    onAddressChange = TODO(),
                    onContinueToPayment = TODO()
                )
            }
            composable(route = Screen.ClothingManagement.route) {
                ClothingManagementScreen(navController)
            }
            composable(route = Screen.EditClothing.route) {
                EditClothingScreen(navController)
            }
            composable(route = Screen.MyCart.route){
                MyCartScreen(navController)
            }
            composable(route = Screen.Landing.route) {
                WelcomeScreen(navController)
            }
            composable(route = Screen.Notification.route) {
                NotificationScreen(
                    navController,
                    notifications = TODO(),
                    onMarkAllAsRead = TODO(),
                    onNotificationClick = TODO(),
                    modifier = TODO()
                )
            }
            composable(route = Screen.PasswordManager.route) {
                PasswordManagerScreen(navController)
            }
            composable(route = Screen.PaymentMethods.route) {
                PaymentMethodScreen(navController)
            }
            composable(route = Screen.PaymentStatus.route) {
                PaymentStatusScreen(
                    navController,
                    onViewOrder = TODO(),
                )
            }
            composable(route = Screen.PrivacyPolicy.route) {
                PrivacyPolicyScreen(navController)
            }
            composable(route = Screen.ProductDetails.route) {
                ProductDetailScreen(
                    navController,
                    modifier = TODO(),
                    product = TODO(),
                    onAddToCart = TODO()
                )
            }
            composable(route = Screen.Splash.route) {
                SplashScreen(navController)
            }
            composable(route = Screen.Signin.route) {
                LoginScreen(
                    navController,
                )
            }
            composable(route = Screen.Search.route) {
                SearchScreen(navController, "Ethiopian Suit")
            }
            composable(route = Screen.Settings.route) {
                SettingsScreen(navController)
            }
            composable(route = Screen.ShippingAddress.route) {
                ShippingAddressScreen(navController)
            }
            composable(route = Screen.UserManagement.route) {
                UserManagementScreen(navController)
            }
        }
    NavHost(
        navController = navController,
        graph = graph,
    )
}
