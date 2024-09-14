package com.example.flowtechticstask.presentation.home

sealed interface HomeInteraction {
    fun onLogOutClick()
    fun refreshData()
    fun clearMessage()
    fun refreshScreenData()
}