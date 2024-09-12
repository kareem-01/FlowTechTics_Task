package com.example.flowtechticstask.presentation.signUp

data class SignUpUiState(
    val name: String = "",
    val age: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val message: String? = null,
)
