package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.navigation.Screen

@Composable
fun CheckoutScreen(
    navController: NavController,
    shippingAddress: Address,
    orderItems: List<OrderItem>,
    onAddressChange: () -> Unit,
    onContinueToPayment: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            TopNavigationBar(title = "Checkout", onClick = { navController.navigate(Screen.MyCart.route) })
            Text(
                text = "Checkout",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )

            ShippingAddressSection(
                address = shippingAddress,
                onAddressChange = onAddressChange
            )

            OrderListSection(
                items = orderItems
            )

            Spacer(modifier = Modifier.weight(1f))

            ContinueToPaymentButton(
                onClick = onContinueToPayment,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun ShippingAddressSection(
    address: Address,
    onAddressChange: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Shipping Address",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = address.label,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )

                    TextButton(
                        onClick = onAddressChange,
                        modifier = Modifier.padding(start = 8.dp)
                    ) {
                        Text(text = "Change")
                    }
                }

                Text(
                    text = address.fullAddress,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

@Composable
fun OrderListSection(
    items: List<OrderItem>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = "Order List",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(items) { item ->
                OrderItemCard(item = item)
            }
        }
    }
}

@Composable
fun OrderItemCard(
    item: OrderItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.name,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Total Price",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Quantity",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "ETB ${item.totalPrice}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = item.quantity.toString(),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

/**
 * Continue to payment button.
 * @param onClick Callback when button is clicked
 */
@Composable
fun ContinueToPaymentButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = "Continue to Payment",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

data class Address(
    val label: String,
    val fullAddress: String
)

data class OrderItem(
    val name: String,
    val quantity: Int,
    val totalPrice: Int
)

@Preview
@Composable
fun CheckoutScreenPreview() {
    MaterialTheme {
        CheckoutScreen(
            shippingAddress = Address(
                label = "Home",
                fullAddress = "Addis Ababa, 4kllio"
            ),
            orderItems = listOf(
                OrderItem(
                    name = "Ethiopian Suit",
                    quantity = 2,
                    totalPrice = 2000
                ),
                OrderItem(
                    name = "Habesha Kemis",
                    quantity = 1,
                    totalPrice = 2500
                ),
                OrderItem(
                    name = "Ethiopian Suit",
                    quantity = 1,
                    totalPrice = 1500
                )
            ),
            onAddressChange = { /* Handle address change */ },
            onContinueToPayment = { /* Handle payment continuation */ },
            navController = TODO()
        )
    }
}