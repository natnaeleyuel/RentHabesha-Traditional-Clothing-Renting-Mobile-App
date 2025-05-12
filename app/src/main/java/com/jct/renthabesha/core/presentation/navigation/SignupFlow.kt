package com.jct.renthabesha.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.createGraph
import com.jct.renthabesha.core.presentation.screens.AddLocationScreen
import com.jct.renthabesha.core.presentation.screens.CompleteProfileScreen
import com.jct.renthabesha.core.presentation.screens.SignupScreen
import com.jct.renthabesha.core.presentation.viewmodels.SignupViewModel
@Composable
fun SignupFlow(
    mainNavController: NavController,
) {
    val signupNavController = rememberNavController()
    val viewModel: SignupViewModel = hiltViewModel()

    LaunchedEffect(viewModel.state.isSuccess) {
        if (viewModel.state.isSuccess) {
            mainNavController.navigate(Screen.Signin.route) {
                popUpTo(Screen.Signup.route) { inclusive = true }
            }
        }
    }

    NavHost(
        navController = signupNavController,
        startDestination = SignupStep.Step1.route
    ) {
        composable(SignupStep.Step1.route) {
            SignupScreen(
                navController = signupNavController,
                viewModel = viewModel
            )
        }
        composable(SignupStep.Step2.route) {
            CompleteProfileScreen(
                navController = signupNavController,
                viewModel = viewModel
            )
        }
        composable(SignupStep.Step3.route) {
            AddLocationScreen(
                navController = signupNavController,
                viewModel = viewModel
            )
        }
    }
}

sealed class SignupStep(val route: String) {
    object Step1 : SignupStep("signup/step1")
    object Step2 : SignupStep("signup/step2")
    object Step3 : SignupStep("signup/step3")
}