package com.example.flowtechticstask

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowtechticstask.domain.usecase.GetLoggedInStatusUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getLoggedInStatusUseCase: GetLoggedInStatusUseCase,
) : ViewModel() {
    private val _isUserLoggedIn: MutableStateFlow<Boolean?> = MutableStateFlow(false)
    val isUserLoggedIn = _isUserLoggedIn.asStateFlow()

    init {
        getLoggedIngStatus()
    }

    private fun getLoggedIngStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            try {

                getLoggedInStatusUseCase().also {
                    _isUserLoggedIn.value = it
                }
            } catch (e: Exception) {
                _isUserLoggedIn.value = false
            }
            Log.d("AuthViewModel", "getLoggedIngStatus: ${_isUserLoggedIn.value}")
        }
    }
}