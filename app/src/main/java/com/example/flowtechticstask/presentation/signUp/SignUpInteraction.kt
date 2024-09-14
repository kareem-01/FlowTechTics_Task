package com.example.flowtechticstask.presentation.signUp

import com.example.flowtechticstask.TextType

interface SignUpInteraction {
    fun signUp()
    fun updateUiState(textType: TextType, text: String)
    fun onLogInClick()
}