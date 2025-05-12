package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.jct.renthabesha.core.domain.models.ClothingItem
import com.jct.renthabesha.core.presentation.components.AddToCartButton
import com.jct.renthabesha.core.presentation.components.ManageUsersCard
import com.jct.renthabesha.core.presentation.components.ProductDescription
import com.jct.renthabesha.core.presentation.components.ProductHeader
import com.jct.renthabesha.core.presentation.components.QuantitySelector
import com.jct.renthabesha.core.presentation.components.RentalInfoSection
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.navigation.Screen
import com.jct.renthabesha.core.presentation.viewmodels.UsersViewModel

@Composable
fun ProductDetailScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    product: ClothingItem,
    onAddToCart: (Int) -> Unit
) {
    var quantity by remember { mutableStateOf(1) }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TopNavigationBar(title = "Product Details", onClick = { navController.navigate(Screen.Home.route) })
            ProductHeader(
                title = product.title,
                category = product.category
            )

            ProductDescription(
                description = product.description
            )

            RentalInfoSection(
                price = product.price,
                rentalPeriod = product.rentalPeriod
            )

            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { newQuantity -> quantity = newQuantity },
                modifier = Modifier.fillMaxWidth()
            )

            AddToCartButton(
                price = product.price * quantity,
                onAddToCart = { onAddToCart(quantity) },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}
