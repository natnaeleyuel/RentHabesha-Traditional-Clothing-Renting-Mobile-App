package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.navigation.Screen

data class CartItem(
    val name: String,
    val pricePerUnit: Int,
    val imageRes: Int,
    var quantity: Int
)

@Composable
fun MyCartScreen(
    navController: NavController
) {
    var cartItems by remember {
        mutableStateOf(
            listOf(
                CartItem("Ethiopian Suit", 1000, R.drawable.img_nine, 2),
                CartItem("Habesha Kemis", 2500, R.drawable.img_twenty_one, 1),
                CartItem("Ethiopian Suit", 1500, R.drawable.img_eight, 1)
            )
        )
    }

    val subTotal = cartItems.sumOf { it.quantity * it.pricePerUnit }
    val deliveryFee = 400
    val total = subTotal + deliveryFee
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
            TopNavigationBar(title = "My Cart", onClick = { navController.navigate(Screen.Home.route) })
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                items(cartItems) { item ->
                    CartItemCard(item) { updatedItem ->
                        cartItems = cartItems.map {
                            if (it.name == item.name && it.imageRes == item.imageRes) updatedItem else it
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp)) // Space between cards
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(4.dp, RoundedCornerShape(16.dp))
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Sub Total")
                    Text("ETB %,d".format(subTotal))
                }
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Delivery Fee")
                    Text("ETB %,d".format(deliveryFee))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Total Cost", fontWeight = FontWeight.Bold)
                    Text("ETB %,d".format(total), fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Proceed to checkout */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00C853))
                ) {
                    Text("Proceed to Checkout", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CartItemCard(item: CartItem, onUpdate: (CartItem) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            Image(
                painter = painterResource(id = item.imageRes),
                contentDescription = item.name,
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.width(17.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(item.name, fontWeight = FontWeight.SemiBold, fontSize = 16.sp)

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("Total Price", fontSize = 12.sp, color = Color.Gray)
                        Text(
                            "ETB %,d".format(item.pricePerUnit * item.quantity),
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .border(1.dp, Color(0xFFB2DFDB), RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Decrease",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    if (item.quantity > 1) {
                                        onUpdate(item.copy(quantity = item.quantity - 1))
                                    }
                                },
                            tint = Color.Black
                        )
                        Text(
                            text = item.quantity.toString(),
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Increase",
                            modifier = Modifier
                                .size(24.dp)
                                .clickable {
                                    onUpdate(item.copy(quantity = item.quantity + 1))
                                },
                            tint = Color.Black
                        )
                    }
                }
            }
        }

        Divider(color = Color.LightGray, thickness = 1.dp)
    }
}
