package com.example.flowtechticstask.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
abstract class BaseViewModel<STATE, UiEFFECT>(initialValue: STATE) : ViewModel() {


    protected val _state: MutableStateFlow<STATE> = MutableStateFlow(initialValue)
    val state = _state.asStateFlow()

    private val _effect = MutableSharedFlow<UiEFFECT>()
    val effect = _effect.asSharedFlow()

    fun <T> tryToExecute(
        request: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                request().also(onSuccess)
            } catch (e: Exception) {
                onError(e)
            }
        }
    }

    protected fun sendUiEffect(effect: UiEFFECT) {
        viewModelScope.launch { _effect.emit(effect) }
    }

    protected fun updateState(reducer: STATE.() -> STATE) {
        _state.value = _state.value.reducer()
    }


}