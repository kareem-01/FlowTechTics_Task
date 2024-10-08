package com.example.flowtechticstask.presentation.signUp

import android.util.Patterns
import androidx.lifecycle.viewModelScope
import com.example.flowtechticstask.TextType
import com.example.flowtechticstask.base.BaseViewModel
import com.example.flowtechticstask.domain.usecase.SignUpUseCase
import com.example.flowtechticstask.utils.StringsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val stringsProvider: StringsProvider,
    private val signUpUseCase: SignUpUseCase,
) : BaseViewModel<SignUpUiState, SignUpUiEffect>(SignUpUiState()),
    SignUpInteraction {
    override fun signUp() {
        if (checkToSignUp())
            tryToExecute({
                signUpUseCase(
                    email = state.value.email,
                    password = state.value.password
                ) { _, message ->
                    if (message != null)
                        updateState { copy(message = message) }
                }
            }, ::onSignUpSuccess, ::onError)
    }

    private fun onSignUpSuccess(unit: Unit) {
        sendUiEffect(SignUpUiEffect.NavigateToHome)
    }

    private fun onError(exception: Exception) {
        updateState { copy(message = exception.message) }
    }


    private fun checkToSignUp(): Boolean {
        clearMessage()
        state.value.let {
            return if (it.email.isEmpty() || it.name.isEmpty() || it.password.isEmpty() || it.confirmPassword.isEmpty() || it.age.isEmpty()) {
                updateState { copy(message = stringsProvider.emptyFields) }
                false
            } else if (it.name.length < 3) {
                updateState { copy(message = stringsProvider.userNameLessThanThree) }
                false
            } else if (it.password.length < 6) {
                updateState { copy(message = stringsProvider.lessThanSixPassword) }
                false
            } else if (Patterns.EMAIL_ADDRESS.matcher(it.email).matches().not()) {
                updateState { copy(message = stringsProvider.invalidEmail) }
                false
            } else if (it.password != it.confirmPassword) {
                updateState { copy(message = stringsProvider.passwordAndConfirmationNotTheSame) }
                false
            } else {
                true
            }
        }
    }

    private fun clearMessage() {
        viewModelScope.launch {
            delay(3000)
            updateState { copy(message = null) }
        }
    }

    override fun updateUiState(textType: TextType, text: String) {
        when (textType) {
            TextType.USERNAME -> updateState { copy(name = text) }
            TextType.EMAIL -> updateState { copy(email = text) }
            TextType.PASSWORD -> updateState { copy(password = text) }
            TextType.CONFIRM_PASSWORD -> updateState { copy(confirmPassword = text) }
            TextType.AGE -> updateState { copy(age = text) }
        }
    }

    override fun onLogInClick() {
        sendUiEffect(SignUpUiEffect.NavigateToLogin)
    }
}