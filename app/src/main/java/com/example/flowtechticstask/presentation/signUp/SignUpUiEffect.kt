package com.example.flowtechticstask.presentation.signUp

sealed interface SignUpUiEffect {
    data object NavigateToHome : SignUpUiEffect
    data object NavigateToLogin : SignUpUiEffect
}