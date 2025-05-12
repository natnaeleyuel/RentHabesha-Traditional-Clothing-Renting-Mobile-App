package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.core.domain.models.NotificationItem
import com.jct.renthabesha.core.presentation.components.BottomNavigationBar
import com.jct.renthabesha.core.presentation.components.NotificationCard
import com.jct.renthabesha.core.presentation.components.NotificationHeader
import com.jct.renthabesha.core.presentation.components.TopNavigationBar

@Composable
fun NotificationScreen(
    navController: NavController,
    notifications: List<NotificationItem>,
    onMarkAllAsRead: () -> Unit,
    onNotificationClick: (NotificationItem) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ){ innerPadding ->
        Column(
            modifier = modifier.fillMaxSize()
                .padding(innerPadding)
        ) {
            TopNavigationBar(title = "Notifications", onClick = { navController.navigate("Home") })
            NotificationHeader(
                title = "Today",
                onMarkAllAsRead = onMarkAllAsRead
            )

            LazyColumn {
                itemsIndexed(
                    items = notifications,
                    key = { _, item -> item.id }
                ) { _, notification ->
                    NotificationCard(
                        notification = notification,
                        onClick = { onNotificationClick(notification) }
                    )
                    Divider(
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}