package com.example.flowtechticstask.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.flowtechticstask.presentation.logIn.LogInScreen
import com.example.flowtechticstask.presentation.signUp.SignUpScreen
import com.example.flowtechticstask.utils.clearBackStackAndNavigate

fun NavGraphBuilder.logIn() {
    composable(Screen.LogIn.route) {
        LogInScreen()
    }
}

fun NavController.toLogIn() {
    clearBackStackAndNavigate(Screen.LogIn)
}

fun NavGraphBuilder.signUp() {
    composable(Screen.SignUp.route) {
        SignUpScreen()
    }
}

fun NavController.toSignUp() {
    clearBackStackAndNavigate(Screen.SignUp)
}

fun NavGraphBuilder.home() {
    composable(Screen.Home.route) {
        //HomeScreen()
    }
}

fun NavController.toHome() {
    clearBackStackAndNavigate(Screen.Home)
}