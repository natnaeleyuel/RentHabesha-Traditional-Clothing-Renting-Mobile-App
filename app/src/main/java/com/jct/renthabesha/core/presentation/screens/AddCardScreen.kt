package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.TopNavigationBar
import com.jct.renthabesha.core.presentation.navigation.Screen

@Composable
fun AddCardScreen(
    modifier: Modifier = Modifier,
    onSaveCard: (CardDetails) -> Unit,
    navController: NavController
) {
    var cardHolderName by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var expiryDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

//            navController.popBackStack() this also works well for navigation back

            TopNavigationBar(title = "Add Card", onClick = { navController.navigate(Screen.PaymentMethods.route) })
            CardInputField(
                label = "Card Holder Name",
                value = cardHolderName,
                onValueChange = { cardHolderName = it },
                placeholder = "Holder Name",
                keyboardOptions = KeyboardOptions.Default
            )

            CardInputField(
                label = "Card Number",
                value = cardNumber,
                onValueChange = { cardNumber = it },
                placeholder = "Card Number",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                CardInputField(
                    label = "Expiry Date",
                    value = expiryDate,
                    onValueChange = { expiryDate = it },
                    placeholder = "MM/YYYY",
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                CardInputField(
                    label = "CVV",
                    value = cvv,
                    onValueChange = { cvv = it },
                    placeholder = "CVV",
                    modifier = Modifier.weight(1f),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
            }

            CheckboxWithLabel(
                label = "Save Card",
                checked = false,
                onCheckedChange = { /* Handle save card preference */ }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    onSaveCard(
                        CardDetails(
                            cardHolderName = cardHolderName,
                            cardNumber = cardNumber,
                            expiryDate = expiryDate,
                            cvv = cvv
                        )
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.medium
            ) {
                Text(text = "Add Card")
            }
        }
    }
}

@Composable
fun CardInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) },
            visualTransformation = visualTransformation,
            keyboardOptions = keyboardOptions,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            )
        )
    }
}

@Composable
fun CheckboxWithLabel(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

data class CardDetails(
    val cardHolderName: String,
    val cardNumber: String,
    val expiryDate: String,
    val cvv: String
)

//@Preview
//@Composable
//fun AddCardScreenPreview() {
//    MaterialTheme {
//        AddCardScreen(
//            onSaveCard = { /* Handle card save */ },
//            onBackClick = { /* Handle back navigation */ }
//        )
//    }
//}
