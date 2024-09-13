package com.example.flowtechticstask.presentation.home

import androidx.annotation.StringRes

data class HomeUiState(
    val isScreenLoading: Boolean = true,
    val isRefreshing: Boolean = false,
    val characters: List<CharacterUiModel> = emptyList(),
    @StringRes val messageResource: Int? = null,
    val nextPage: Int = 1,
    val totalPages: Int = Int.MAX_VALUE,
    val hasInternet: Boolean = false,
)

data class CharacterUiModel(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val status: String,
    val species: String,
)
