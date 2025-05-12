package com.jct.renthabesha.core.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jct.renthabesha.core.presentation.components.TextField
import com.jct.renthabesha.R
import com.jct.renthabesha.core.presentation.navigation.Screen
import com.jct.renthabesha.core.presentation.navigation.SignupStep
import com.jct.renthabesha.core.presentation.theme.Brand
import com.jct.renthabesha.core.presentation.viewmodels.SigninEvent
import com.jct.renthabesha.core.presentation.viewmodels.SignupEvent
import com.jct.renthabesha.core.presentation.viewmodels.SignupViewModel

@Composable
fun AddLocationScreen(
    navController: NavController,
    viewModel: SignupViewModel
) {
    val state = viewModel.state

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 100.dp),
        ) {

            Icon(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .align(Alignment.CenterHorizontally),
                painter = painterResource(id = R.drawable.location_icon),
                contentDescription = "Location Icon",
                tint = Brand
            );

            Spacer(modifier = Modifier.height(25.dp));

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.your_location_headline),
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp));

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.add_location_description),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxSize()
            ){

                TextField(
                    value = viewModel.address,
                    modifier = Modifier.fillMaxWidth(),
                    label = stringResource(id = R.string.your_location_label),
                    placeholder = stringResource(id = R.string.location),
                    onValueChanged = { viewModel.onEvent(SignupEvent.FieldUpdate(address = it)) },
                )

                Spacer(modifier = Modifier.height(40.dp));

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        if (viewModel.validateStep3()) {
                            viewModel.onEvent(SignupEvent.Submit)
                            navController.navigate(Screen.Signin.route)
                        }
                    },
                    enabled = !state.isLoading,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Brand,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(size = 40.dp)
                ) {
                    if(state.isLoading){
                        CircularProgressIndicator(color = Color.White)
                    }else{
                        Text(text = stringResource(id = R.string.add_location),
                            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium)
                        )
                    }
                }
                state.error?.let{
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(it, color = Color.Red)
                }
                LaunchedEffect(viewModel.state.isSuccess) {
                    if (viewModel.state.isSuccess) {
                        navController.navigate(Screen.Signin.route){
                            popUpTo(Screen.Signup.route){
                                inclusive = true
                            }
                        }
                    }
                }
            }
        }
    }
}














