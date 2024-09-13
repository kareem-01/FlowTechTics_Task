package com.example.flowtechticstask.presentation.logIn

import android.util.Patterns
import com.example.flowtechticstask.TextType
import com.example.flowtechticstask.base.BaseViewModel
import com.example.flowtechticstask.domain.usecase.LogInUseCase
import com.example.flowtechticstask.utils.StringsProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val stringsProvider: StringsProvider,
    private val logInUseCase: LogInUseCase,
) :
    BaseViewModel<LogInUiState, LogInUiEffect>(LogInUiState()),
    LogInInteraction {

    override fun onLogInClicked() {
        if (checkToLogIn()) {
            tryToExecute(
                {
                    logInUseCase(
                        state.value.email,
                        state.value.password
                    ) { _, message ->
                        if (message != null)
                            updateState { copy(message = message) }
                    }
                },
                ::onSuccess,
                ::onError
            )
        }
    }

    private fun onSuccess(unit: Unit) = sendUiEffect(LogInUiEffect.NavigateToHome)

    private fun onError(exception: Exception) {
        updateState { copy(message = exception.message) }
    }

    private fun checkToLogIn(): Boolean {
        state.value.let {
            return if (it.email.isBlank() || it.password.isBlank()) {
                updateState { copy(message = stringsProvider.emptyFields) }
                false
            } else if (it.password.length < 6) {
                updateState { copy(message = stringsProvider.lessThanSixPassword) }
                false
            } else if (Patterns.EMAIL_ADDRESS.matcher(it.email).matches().not()) {
                updateState { copy(message = stringsProvider.invalidEmail) }
                false
            } else
                true
        }
    }

    override fun onSignUpClicked() {
        sendUiEffect(LogInUiEffect.NavigateToSignUp)
    }

    override fun updateUiState(textType: TextType, text: String) {
        when (textType) {
            TextType.EMAIL -> updateState { copy(email = text) }
            TextType.PASSWORD -> updateState { copy(password = text) }
            else -> {}
        }
    }

    override fun clearMessage() {
        updateState { copy(message = null) }
    }
}