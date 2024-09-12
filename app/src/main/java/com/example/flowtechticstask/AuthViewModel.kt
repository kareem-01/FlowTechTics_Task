package com.example.flowtechticstask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowtechticstask.domain.usecase.GetLoggedInStatusUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val getLoggedInStatusUseCase: GetLoggedInStatusUseCase,
) : ViewModel() {
    private val _isUserLoggedIn: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val isUserLoggedIn = _isUserLoggedIn.asStateFlow()

    init {
        getLoggedIngStatus()
    }

    private fun getLoggedIngStatus() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                getLoggedInStatusUseCase().also {
                    _isUserLoggedIn.value = true
                }
            } catch (e: Exception) {
                _isUserLoggedIn.value = false
            }

        }
    }
}