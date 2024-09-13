package com.example.flowtechticstask.presentation.logIn

data class LogInUiState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val message: String? = null,
)

