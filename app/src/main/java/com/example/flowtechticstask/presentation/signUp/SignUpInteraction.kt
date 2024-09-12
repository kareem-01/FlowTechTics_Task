package com.example.flowtechticstask.presentation.signUp

import com.example.flowtechticstask.TextType

interface SignUpInteraction {
    fun signUp()
    fun updateAllStates(
        userName: String,
        email: String,
        password: String,
        rePassword: String,
        phoneNumber: String,
    )

    fun updateUiState(textType: TextType, text: String)
    fun onLogInClick()
}