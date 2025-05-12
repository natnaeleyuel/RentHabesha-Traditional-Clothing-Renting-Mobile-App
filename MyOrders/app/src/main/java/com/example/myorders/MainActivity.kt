package com.example.myorders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myorders.ui.theme.MyOrdersTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyOrdersTheme{
                OrdersScreen()
            }
        }
    }
}

@Composable
fun OrdersScreen() {
    var selectedTab by remember { mutableStateOf(0) }
    val tabs = listOf("Active", "Completed")

    Scaffold(
        bottomBar = { BottomNavigationBar(selectedTab = 1) }
    ) { padding ->
        Column(modifier = Modifier.padding(vertical = 30.dp)) {
            TopBar()

            TabRow(selectedTabIndex = selectedTab, modifier = Modifier.fillMaxWidth()) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title) }
                    )
                }
            }

            val orders = listOf(
                OrderItem("Ethiopian Suit", "ETB 2,000", 2),
                OrderItem("Habesha Kemis", "ETB 2,500", 1),
                OrderItem("Ethiopian Suit", "ETB 1,500", 1),
                OrderItem("Netela", "ETB 1,000", 2)
            )

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(orders) { order ->
                    OrderCard(order)
                    HorizontalDivider()
                }
            }
        }
    }
}

data class OrderItem(val title: String, val price: String, val quantity: Int)

@Composable
fun TopBar() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
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

fun OrderCard(order: OrderItem) {
    Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
        Surface(
            modifier = Modifier.size(70.dp),
            shape = MaterialTheme.shapes.medium,
            color = Color.LightGray
        ) {
//             Image placeholder
            Box(contentAlignment = Alignment.Center) {
                Icon(Icons.Default.Home, contentDescription = order.title)
//                Image
            }
        }
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(order.title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Total Price  ${order.price}", fontSize = 14.sp)
            Text("Quantity  ${order.quantity}", fontSize = 14.sp, fontWeight = FontWeight.Bold)
        }
    }
}


@Composable
fun BottomNavigationBar(selectedTab: Int) {
    NavigationBar(
        containerColor = Color.White,
        tonalElevation = 4.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            selected = selectedTab == 0,
            onClick = { /* Handle Home */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.ShoppingCart, contentDescription = "Orders") },
//            ShoppingBag
            selected = selectedTab == 1,
            onClick = { /* Handle Orders */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.AccountCircle, contentDescription = "Profile") },
            selected = selectedTab == 2,
            onClick = { /* Handle Profile */ }
        )
    }
}
