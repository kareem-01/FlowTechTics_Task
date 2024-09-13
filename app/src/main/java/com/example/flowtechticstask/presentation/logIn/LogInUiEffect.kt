package com.example.flowtechticstask.presentation.logIn

sealed interface LogInUiEffect {
    data object NavigateToHome : LogInUiEffect
    data object NavigateToSignUp : LogInUiEffect
}