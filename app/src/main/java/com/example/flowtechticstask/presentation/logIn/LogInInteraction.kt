package com.example.flowtechticstask.presentation.logIn

import com.example.flowtechticstask.TextType

interface LogInInteraction {
    fun onLogInClicked()
    fun onSignUpClicked()
    fun updateUiState(textType: TextType, text: String)
    fun clearMessage()
}