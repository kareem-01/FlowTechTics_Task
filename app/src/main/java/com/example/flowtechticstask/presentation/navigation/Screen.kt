package com.example.flowtechticstask.presentation.navigation

private const val SIGN_UP = "signUp"
private const val LOG_IN = "logIn"
private const val HOME = "home"

sealed class Screen(val route: String) {
    data object SignUp : Screen(SIGN_UP)
    data object LogIn : Screen(LOG_IN)
    data object Home : Screen(HOME)
}