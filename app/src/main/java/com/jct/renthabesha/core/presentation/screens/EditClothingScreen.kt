package com.jct.renthabesha.core.presentation.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.jct.renthabesha.R
import com.jct.renthabesha.core.data.remote.api.ClothingItemResponse
import com.jct.renthabesha.core.presentation.components.ButtonCompos
import com.jct.renthabesha.core.presentation.components.ProfilePicture
import com.jct.renthabesha.core.presentation.components.TextField
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.navigation.Screen
import com.jct.renthabesha.core.presentation.theme.Brand
import com.jct.renthabesha.core.presentation.theme.focusedTextFieldStroke
import com.jct.renthabesha.core.presentation.theme.focusedTextFieldText
import com.jct.renthabesha.core.presentation.theme.unfocusedTextFieldStroke
import com.jct.renthabesha.core.presentation.theme.unfocusedTextFieldText
import com.jct.renthabesha.core.presentation.viewmodels.ClothingViewModel
import com.jct.renthabesha.core.presentation.viewmodels.SignupEvent
import dagger.hilt.android.lifecycle.HiltViewModel
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditClothingScreen(
    navController: NavHostController,
    viewModel: ClothingViewModel = hiltViewModel(),
    clothingItemId: String? = null
) {
    val clothingItem by viewModel.clothingItems.collectAsState().let { state ->
        remember(clothingItemId) {
            derivedStateOf<ClothingItemResponse?> {
                state.value.firstOrNull { it._id == clothingItemId }
            }
        }
    }

    val categories = listOf("Men", "Women", "Kids")
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedRentalPeriod by remember { mutableStateOf(false) }
    val rentalPeriods = listOf("Daily", "Weekly", "Monthly")

    var title by remember { mutableStateOf(clothingItem?.title ?: "") }
    var description by remember { mutableStateOf(clothingItem?.description?.split("\n\nCare Instructions:")?.firstOrNull() ?: "") }
    var careInstructions by remember {
        mutableStateOf(
            clothingItem?.description?.split("\n\nCare Instructions:")?.getOrNull(1) ?: ""
        )
    }
    var price by remember { mutableStateOf(clothingItem?.pricePerDay?.toString() ?: "") }
    var selectedCategory by remember { mutableStateOf(clothingItem?.type ?: "") }
    var selectedRentalPeriod by remember { mutableStateOf("Daily") } // Default to Daily
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        imageUri = uri
    }

    Surface(
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 50.dp)
    ) {
        Column {
            TopNavigationBar(
                title = "Edit Clothing",
                onClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clickable { imagePicker.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)) }
                    .border(1.dp, Color.Gray, RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                when {
                    imageUri != null -> {
                        Image(
                            painter = rememberImagePainter(imageUri),
                            contentDescription = "Selected image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    !clothingItem?.images.isNullOrEmpty() -> {
                        // Display existing image from URL
                        Image(
                            painter = rememberImagePainter(clothingItem?.images?.first()),
                            contentDescription = "Current image",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                    else -> {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.image_icon),
                                contentDescription = "Add image",
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                text = "Change Clothing Image",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 16.dp),
            ) {
                Spacer(modifier = Modifier.height(5.dp))

                Column(
                    modifier = Modifier.fillMaxSize()
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Name",
                        placeholder = "Clothing Name",
                        onValueChanged = { title = it },
                        value = title
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        ExposedDropdownMenuBox(
                            expanded = expandedCategory,
                            onExpandedChange = { expandedCategory = !expandedCategory },
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = selectedCategory,
                                onValueChange = {},
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),
                                placeholder = { Text("Category") },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCategory)
                                },
                                readOnly = true,
                                colors = TextFieldDefaults.colors(
                                    focusedIndicatorColor = MaterialTheme.colorScheme.focusedTextFieldStroke,
                                    unfocusedIndicatorColor = MaterialTheme.colorScheme.unfocusedTextFieldStroke,
                                    focusedTextColor = MaterialTheme.colorScheme.focusedTextFieldText,
                                    unfocusedTextColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                                    focusedContainerColor = White,
                                    unfocusedContainerColor = White
                                ),
                                shape = RoundedCornerShape(20.dp),
                            )

                            ExposedDropdownMenu(
                                expanded = expandedCategory,
                                onDismissRequest = { expandedCategory = false }
                            ) {
                                categories.forEach { category ->
                                    DropdownMenuItem(
                                        text = { Text(category) },
                                        onClick = {
                                            selectedCategory = category
                                            expandedCategory = false
                                        }
                                    )
                                }
                            }
                        }

                        TextField(
                            modifier = Modifier.weight(1f),
                            label = "Price",
                            placeholder = "0.00",
                            onValueChanged = { price = it },
                            value = price,
                        )
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    ExposedDropdownMenuBox(
                        expanded = expandedRentalPeriod,
                        onExpandedChange = { expandedRentalPeriod = !expandedRentalPeriod },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = selectedRentalPeriod,
                            onValueChange = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .menuAnchor(),
                            placeholder = { Text("Rental Period") },
                            label = { Text("Rental Period") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedRentalPeriod)
                            },
                            readOnly = true,
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = MaterialTheme.colorScheme.focusedTextFieldStroke,
                                unfocusedIndicatorColor = MaterialTheme.colorScheme.unfocusedTextFieldStroke,
                                focusedTextColor = MaterialTheme.colorScheme.focusedTextFieldText,
                                unfocusedTextColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                                focusedContainerColor = White,
                                unfocusedContainerColor = White
                            ),
                            shape = RoundedCornerShape(20.dp),
                        )

                        ExposedDropdownMenu(
                            expanded = expandedRentalPeriod,
                            onDismissRequest = { expandedRentalPeriod = false }
                        ) {
                            rentalPeriods.forEach { period ->
                                DropdownMenuItem(
                                    text = { Text(period) },
                                    onClick = {
                                        selectedRentalPeriod = period
                                        expandedRentalPeriod = false
                                    }
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(25.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth()
                            .height(100.dp),
                        label = "Description",
                        placeholder = "Describe the clothing, design details",
                        onValueChanged = { description = it },
                        value = description,
                    )

                    Spacer(modifier = Modifier.height(25.dp))

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = "Care Instruction",
                        placeholder = "Care Instruction",
                        onValueChanged = { careInstructions = it },
                        value = careInstructions
                    )

                    Spacer(modifier = Modifier.height(40.dp))

                    ButtonCompos(
                        modifier = Modifier.fillMaxWidth(),
                        onClicked = {
                            clothingItemId?.let { id ->
                                viewModel.updateClothingItem(
                                    id = id,
                                    title = title,
                                    description = "$description\n\nCare Instructions: $careInstructions",
                                    pricePerDay = price.toDoubleOrNull() ?: 0.0,
                                    type = selectedCategory,
                                    location = clothingItem?.location ?: "Addis Ababa",
                                    imageUri = imageUri
                                )
                                navController.popBackStack()
                            }
                        },
                        text = R.string.update,
                    )
                }
            }
        }
    }
}