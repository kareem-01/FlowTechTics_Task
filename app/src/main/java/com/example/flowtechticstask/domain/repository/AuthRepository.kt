package com.example.flowtechticstask.domain.repository

interface AuthRepository {
    suspend fun signUp()
    suspend fun logIn()
    suspend fun logOut()
    suspend fun isLoggedIn(): Boolean
}