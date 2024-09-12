package com.example.flowtechticstask.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.flowtechticstask.presentation.logIn.LogInScreen

fun NavGraphBuilder.logIn() {
    composable(Screen.LogIn.route) {
        LogInScreen()
    }
}

fun NavController.toLogIn() {
    navigate(Screen.LogIn.route)
}

fun NavGraphBuilder.signUp() {
    composable(Screen.SignUp.route) {
        //SignUpScreen()
    }
}

fun NavController.toSignUp() {
    navigate(Screen.SignUp.route)
}

fun NavGraphBuilder.home() {
    composable(Screen.Home.route) {
        //HomeScreen()
    }
}

fun NavController.toHome() {
    navigate(Screen.Home.route)
}