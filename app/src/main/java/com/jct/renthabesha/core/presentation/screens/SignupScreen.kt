package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.ButtonCompos
import com.jct.renthabesha.core.presentation.components.TextField
import com.jct.renthabesha.core.presentation.navigation.Screen
import com.jct.renthabesha.core.presentation.navigation.SignupStep
import com.jct.renthabesha.core.presentation.theme.Brand
import com.jct.renthabesha.core.presentation.theme.Poppins
import com.jct.renthabesha.core.presentation.theme.TextStrong
import com.jct.renthabesha.core.presentation.viewmodels.SignupEvent
import com.jct.renthabesha.core.presentation.viewmodels.SignupViewModel
import timber.log.Timber

@Composable
fun SignupScreen(
    navController: NavController,
    viewModel: SignupViewModel,
) {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 100.dp),
        ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.create_account),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(5.dp))

            Column(
                modifier = Modifier.fillMaxSize()
            ){

                TextField(
                    value = viewModel.name,
                    onValueChanged = {viewModel.onEvent(SignupEvent.FieldUpdate(name = it))},
                    modifier = Modifier.fillMaxWidth(),
                    label =  stringResource(id = R.string.fullname),
                    placeholder = stringResource(id = R.string.fullname)
                )

                Spacer(modifier = Modifier.height(25.dp))

                TextField(
                    value = viewModel.email,
                    onValueChanged = {viewModel.onEvent(SignupEvent.FieldUpdate(email = it))},
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.email),
                    placeholder = stringResource(id = R.string.email)
                );

                Spacer(modifier = Modifier.height(25.dp));

                TextField(
                    value = viewModel.password,
                    onValueChanged = {viewModel.onEvent(SignupEvent.FieldUpdate(password = it))},
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.password),
                    placeholder = stringResource(id = R.string.password)
                );

                Spacer(modifier = Modifier.height(40.dp));

                ButtonCompos(
                    modifier = Modifier,
                    onClicked = {
                        if (viewModel.validateStep1()) {
                            navController.navigate(SignupStep.Step2.route)
                        } else {
                            viewModel.onEvent(SignupEvent.SetError("Please fill all fields correctly"))
                        }
                    },
                    R.string.sign_up
                )

                Timber.tag("Validation").d("Result: ${viewModel.validateStep1()}")

                Spacer(modifier = Modifier.height(25.dp))

                Box(
                    modifier = Modifier.fillMaxHeight(0.8f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter,
                ){
                    Row {
                        Text(
                            text = "Already have an account? ",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = if (isSystemInDarkTheme()) Color.White else TextStrong,
                                fontFamily = Poppins
                            )
                        )
                        Text(
                            text = "Sign In",
                            modifier = Modifier.clickable { navController.navigate(Screen.Signin.route) },
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Brand,
                                fontFamily = Poppins,
                                textDecoration = TextDecoration.Underline
                            ),
                        )
                    }
                }
            }
        }
    }
}














