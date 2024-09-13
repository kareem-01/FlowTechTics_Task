package com.example.flowtechticstask.presentation.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.flowtechticstask.R
import com.example.flowtechticstask.base.BaseViewModel
import com.example.flowtechticstask.domain.models.CharactersPaging
import com.example.flowtechticstask.domain.usecase.GetAllCharactersUseCase
import com.example.flowtechticstask.domain.usecase.LogOutUseCase
import com.example.flowtechticstask.utils.NetworkUtils
import com.example.flowtechticstask.utils.StringsProvider
import com.example.flowtechticstask.utils.toNetworkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val networkUtils: NetworkUtils,
    private val stringsProvider: StringsProvider,
) :
    BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteraction {
    private val charactersHandlerException =
        CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.i("charactersHandlerException: ", coroutineContext.toString())
            throwable.localizedMessage?.let { Log.i("charactersHandlerException: ", it) }
            updateState { copy(isScreenLoading = false, isRefreshing = false) }
            updateState { copy(messageResource = throwable.toNetworkError()) }
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchCharacters()
        }
    }

    private suspend fun fetchCharacters() {
        if (!networkUtils.isNetworkAvailable()) {
            updateState {
                copy(
                    messageResource = R.string.noInternetConnection,
                    isScreenLoading = false,
                    isRefreshing = false,
                    hasInternet = false
                )
            }
            return
        }
        updateState { copy(hasInternet = true) }
        tryToExecute(
            { getAllCharactersUseCase(state.value.nextPage) },
            ::onSuccess,
            ::onError,
            charactersHandlerException
        )
    }

    private fun onSuccess(characters: CharactersPaging) {
        updateState {
            copy(
                isScreenLoading = false,
                isRefreshing = false,
                characters = state.value.characters + characters.characters.toUiModel(),
                totalPages = characters.pages,
                nextPage = nextPage + 1
            )
        }
    }

    private fun onError(exception: Exception) {
        updateState { copy(isScreenLoading = false, isRefreshing = false) }
        updateState { copy(messageResource = exception.toNetworkError()) }
    }

    override fun onLogOutClick() {
        tryToExecute({ logOutUseCase() }, ::onLogOutSuccess, ::onLogOutError)
    }

    private fun onLogOutError(exception: Exception) {
        updateState { copy(messageResource = exception.toNetworkError()) }
    }

    private fun onLogOutSuccess(unit: Unit) {
        sendUiEffect(HomeUiEffect.NavigateToLogin)
    }

    override fun refreshData() {
        state.value.apply {
            if (nextPage > totalPages) {
                updateState { copy(messageResource = R.string.no_more_data) }
                return
            }
        }
        updateState { copy(isRefreshing = true) }
        viewModelScope.launch(Dispatchers.IO) {
            fetchCharacters()
        }
    }

    override fun clearMessage() {
        updateState { copy(messageResource = null) }
    }

}