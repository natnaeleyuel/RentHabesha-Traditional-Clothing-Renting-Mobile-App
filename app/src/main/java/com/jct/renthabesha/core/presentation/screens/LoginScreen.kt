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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.components.ButtonCompos
import com.jct.renthabesha.core.presentation.components.TextField
import com.jct.renthabesha.core.presentation.theme.Brand
import com.jct.renthabesha.core.presentation.theme.Poppins
import com.jct.renthabesha.core.presentation.theme.TextStrong
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.navigation.Screen
import com.jct.renthabesha.core.presentation.viewmodels.SigninEvent
import com.jct.renthabesha.core.presentation.viewmodels.SigninViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: SigninViewModel = hiltViewModel(),
) {
    val state = viewModel.state
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 100.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.sign_in),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxSize()
            ){

                TextField(
                    value = state.fields.email,
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.email),
                    placeholder = stringResource(id = R.string.email),
                    onValueChanged = { viewModel.onEvent(SigninEvent.FieldUpdate(email = it)) },
                );

                Spacer(modifier = Modifier.height(25.dp));

                TextField(
                    value = state.fields.password,
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.password),
                    placeholder = stringResource(id = R.string.password),
                    onValueChanged = { viewModel.onEvent(SigninEvent.FieldUpdate(password = it)) },

                );

                Spacer(modifier = Modifier.height(40.dp));

                ButtonCompos(
                    modifier = Modifier,
                    onClicked = { viewModel.onEvent(SigninEvent.Submit) },
                    R.string.sign_in,
                )

                state.error?.let{
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(it, color = Color.Red)
                }

                LaunchedEffect(state.isSuccess) {
                    if (state.isSuccess) {
                        navController.navigate(Screen.Home.route) {
                            popUpTo(Screen.Signin.route) { inclusive = true }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                Box(
                    modifier = Modifier.fillMaxHeight(0.8f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ){
                    Row {
                        Text(
                            text = "Don't have an account? ",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = if (isSystemInDarkTheme()) Color.White else TextStrong,
                                fontFamily = Poppins
                            )
                        )
                        Text(
                            text = "Sign Up",
                            modifier = Modifier.clickable { navController.navigate("signup") },
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = Brand,
                                fontFamily = Poppins,
                                textDecoration = TextDecoration.Underline
                            )
                        )
                    }
                }
            }
        }
    }
}














