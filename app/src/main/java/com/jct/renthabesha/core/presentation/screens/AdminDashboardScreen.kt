package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.RecentOrdersCard
import com.jct.renthabesha.core.presentation.components.StatusCard
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.theme.AmberStatus
import com.jct.renthabesha.core.presentation.theme.GreenStatus

@Composable
fun AdminDashboardScreen(
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopNavigationBar(title = "Admin Dashboard", onClick = { navController.navigate("Home") })
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                item { StatusCard(title = "Total Users", value = "24", onClick = { navController.navigate("UserManagement") } )}
                item { StatusCard(title = "Total Clothing", value = "30", onClick = { navController.navigate("ClothingManagement") } )}
                item { StatusCard(title = "Total Orders", value = "33") }
                item { StatusCard(title = "Completed", value = "12") }
                item { StatusCard(title = "Pending", value = "21") }
            }

            Text(
                text = "Recent Orders",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                RecentOrdersCard(
                    customerName = "John Abebe",
                    productName = "Ethiopian Suit",
                    amount = "ETB 1,200",
                    status = "Pending",
                    time = "2h ago",
                    statusColor = AmberStatus
                )

                RecentOrdersCard(
                    customerName = "Sara Tekle",
                    productName = "Habesha Kemis",
                    amount = "ETB 1,500",
                    status = "Completed",
                    time = "4h ago",
                    statusColor = GreenStatus
                )

                RecentOrdersCard(
                    customerName = "Meskerem Alemu",
                    productName = "Natala",
                    amount = "ETB 800",
                    status = "Pending",
                    time = "1h ago",
                    statusColor = AmberStatus
                )
            }
        }
    }
}