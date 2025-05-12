package com.jct.renthabesha.core.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag

val navigationItems = listOf(
    NavigationItem(
        title = "Home",
        icon = Icons.Default.Home,
        route = "Home"
    ),

    NavigationItem(
        title = "Orders",
        icon = Icons.Default.ShoppingBag,
        route = "Orders"
    ),

    NavigationItem(
        title = "Profile",
        icon = Icons.Default.AccountCircle,
        route = "Profile"
    )
)