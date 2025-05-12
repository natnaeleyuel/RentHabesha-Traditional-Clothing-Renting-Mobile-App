package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.ButtonCompos
import com.jct.renthabesha.core.presentation.components.ProfilePicture
import com.jct.renthabesha.core.presentation.navigation.SignupStep
import com.jct.renthabesha.core.presentation.theme.bodyText
import com.jct.renthabesha.core.presentation.theme.focusedTextFieldStroke
import com.jct.renthabesha.core.presentation.theme.focusedTextFieldText
import com.jct.renthabesha.core.presentation.theme.unfocusedTextFieldStroke
import com.jct.renthabesha.core.presentation.theme.unfocusedTextFieldText
import com.jct.renthabesha.core.presentation.viewmodels.SignupEvent
import com.jct.renthabesha.core.presentation.viewmodels.SignupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteProfileScreen(
    navController: NavController,
    viewModel: SignupViewModel
) {
    val genders = listOf("Male", "Female")
    var expanded by remember { mutableStateOf(false) }
    val focusRequester1 = remember { FocusRequester() };
    val focusRequester2 = remember { FocusRequester() };

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 100.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.complete_profile),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height((25.dp)))

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                ProfilePicture(
                    initialImageRes = R.drawable.image_icon,
                    onImageSelected = { uri -> viewModel.onEvent(SignupEvent.ProfileImageUpdate(uri = uri))}
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Column(
                modifier = Modifier.fillMaxSize()
            ){
                Text(
                    modifier = Modifier.clickable {
                        focusRequester1.requestFocus()
                    },
                    text = "Phone Number",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = viewModel.phone,
                    onValueChange = { viewModel.onEvent(SignupEvent.FieldUpdate(phone = it)) },
                    modifier = Modifier.fillMaxWidth()
                        .focusRequester(focusRequester1),
                    placeholder = { Text("Enter phone number") },
                    leadingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.width(70.dp).padding(start = 5.dp, end = 5.dp)
                        ) {
                            Text(
                                text = "  +251",
                                color = MaterialTheme.colorScheme.bodyText,
                                fontWeight = FontWeight.Normal
                            )
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = "Verified",
                                tint = MaterialTheme.colorScheme.bodyText,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "|",
                                color = MaterialTheme.colorScheme.bodyText,
                                fontWeight = FontWeight.Normal
                            )
                        }
                    },
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = MaterialTheme.colorScheme.focusedTextFieldStroke,
                        unfocusedIndicatorColor = MaterialTheme.colorScheme.unfocusedTextFieldStroke,
                        focusedTextColor = MaterialTheme.colorScheme.focusedTextFieldText,
                        unfocusedTextColor = MaterialTheme.colorScheme.unfocusedTextFieldText,
                        focusedContainerColor = White,
                        unfocusedContainerColor = White
                    ),
                    shape = RoundedCornerShape(20.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    modifier = Modifier.clickable {
                        focusRequester2.requestFocus()
                    },
                    text = "Gender",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(20.dp))

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = viewModel.gender,
                        onValueChange = {viewModel.onEvent(SignupEvent.FieldUpdate(gender = it))},
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                            .focusRequester(focusRequester2),
                        placeholder = { Text("Select") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
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
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        genders.forEach { selectionOption ->
                            DropdownMenuItem(
                                text = { Text(selectionOption) },
                                onClick = {
                                    viewModel.onEvent(SignupEvent.FieldUpdate(gender = selectionOption))
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(40.dp));

                ButtonCompos(
                    modifier = Modifier,
                    onClicked = {
                        if (viewModel.validateStep2()) {
                            navController.navigate(SignupStep.Step3.route)
                        } else {
                            viewModel.onEvent(SignupEvent.SetError("Please fill all fields correctly"))
                        }
                    },
                    R.string.complete_profile_butn
                )
            }
        }
    }
}














