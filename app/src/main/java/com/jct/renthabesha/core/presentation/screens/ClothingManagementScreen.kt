package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.ManageClothingCard
import com.jct.renthabesha.core.presentation.components.RecentOrdersCard
import com.jct.renthabesha.core.presentation.components.StatusCard
import com.jct.renthabesha.core.presentation.components.SummaryClothingCard
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.theme.AmberStatus
import com.jct.renthabesha.core.presentation.theme.GreenStatus

@Composable
fun ClothingManagementScreen(navController: NavController) {
    val scrollState = rememberScrollState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopNavigationBar(title = "Clothing Management", onClick = { navController.navigate("AdminDashboard") })
            Text(
                text = "Clothing Management",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            SummaryClothingCard(
                totalItems = 3,
                totalValue = "ETB 5,000",
                primaryOwner = "Kaleb Addisu"
            )

            Text(
                text = "Clothing Items",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(top = 8.dp)
            )

            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                ManageClothingCard(
                    name = "Ethiopian Suit",
                    price = "ETB 2,500",
                    owner = "Misrak Kinde"
                )

                ManageClothingCard(
                    name = "Habesha Kemis",
                    price = "ETB 1,500",
                    owner = "Meklit Dagne"
                )

                ManageClothingCard(
                    name = "Ethiopian Suit",
                    price = "ETB 1,500",
                    owner = "Kaleb Addisu"
                )
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                FloatingActionButton(
                    onClick = { /* Handle add new clothing */ },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.align(Alignment.BottomEnd)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add new clothing"
                    )
                }
            }
        }
    }
}
