package com.example.flowtechticstask.presentation.home

import androidx.lifecycle.viewModelScope
import com.example.flowtechticstask.R
import com.example.flowtechticstask.base.BaseViewModel
import com.example.flowtechticstask.domain.models.CharactersPaging
import com.example.flowtechticstask.domain.usecase.GetAllCharactersUseCase
import com.example.flowtechticstask.domain.usecase.LogOutUseCase
import com.example.flowtechticstask.utils.NetworkUtils
import com.example.flowtechticstask.utils.toNetworkError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val logOutUseCase: LogOutUseCase,
    private val networkUtils: NetworkUtils,
) :
    BaseViewModel<HomeUiState, HomeUiEffect>(HomeUiState()), HomeInteraction {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchCharacters()
        }
    }

    private suspend fun fetchCharacters() {
        tryToExecute(
            { getAllCharactersUseCase(state.value.nextPage) },
            ::onSuccess,
            ::onError,
        )
    }

    private fun onSuccess(characters: CharactersPaging) {
        updateState {
            copy(
                characters = state.value.characters + characters.characters.toUiModel(),
                isScreenLoading = false,
                isPageRefreshing = true,
                isScreenRefreshing = false,
                totalPages = characters.pages,
                nextPage = nextPage + 1,
                hasInternet = true
            )
        }
    }

    private fun onError(exception: Exception) {
        updateState {
            copy(
                isScreenLoading = false,
                isPageRefreshing = false,
                isScreenRefreshing = false,
            )
        }
        if (!networkUtils.isNetworkAvailable()) {
            updateState {
                if (state.value.messageResource == null)
                    copy(messageResource = R.string.noInternetConnection, hasInternet = false)
                else
                    copy(
                        hasInternet = false
                    )
            }
            return
        }
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
                updateState {
                    copy(
                        messageResource = R.string.no_more_data,
                        isPageRefreshing = false
                    )
                }
                return
            }
        }
        updateState { copy(isPageRefreshing = true) }
        viewModelScope.launch(Dispatchers.IO) {
            fetchCharacters()
        }
    }

    override fun clearMessage() {
        updateState { copy(messageResource = null) }
    }

    override fun refreshScreenData() {
        updateState { copy(nextPage = 1, isScreenRefreshing = true) }
        viewModelScope.launch(Dispatchers.IO) {
            fetchCharacters()
        }
    }

}