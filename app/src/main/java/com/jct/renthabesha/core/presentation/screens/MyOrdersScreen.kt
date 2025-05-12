package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.BottomNavigationBar
import com.jct.renthabesha.core.presentation.components.OrderItemCard
import com.jct.renthabesha.core.presentation.components.TopNavigationBar

@Composable
fun MyOrdersScreen(
    navController: NavController
) {
    val tabs = listOf("Active", "Completed")
    var selectedTabIndex by remember { mutableStateOf(0) }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ){ innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding))
        {
            TopNavigationBar(title = "My Orders", onClick = { navController.navigate("Home") })
            TabRow(selectedTabIndex = selectedTabIndex) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            val orders = when (selectedTabIndex) {
                0 -> listOf(
                    Triple(R.drawable.img_nine, "Ethiopian Suit", Pair("ETB 2,000", 2)),
                    Triple(R.drawable.img_twenty_one, "Habesha Kemis", Pair("ETB 2,500", 1)),
                    Triple(R.drawable.img_eight, "Ethiopian Suit", Pair("ETB 1,500", 1)),
                    Triple(R.drawable.img_twelve, "Netela", Pair("ETB 1,000", 2))
                )
                else -> listOf()
            }

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(orders) { (image, title, priceQty) ->
                    OrderItemCard(
                        imageRes = image,
                        title = title,
                        totalPrice = priceQty.first,
                        quantity = priceQty.second
                    )
                    Divider()
                }
            }
        }
    }
}
