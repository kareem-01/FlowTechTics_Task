package com.example.flowtechticstask.domain.repository

interface AuthRepository {
    suspend fun signUp(email: String, password: String, onResult: (Boolean, String?) -> Unit)
    suspend fun logIn(email: String, password: String, onResult: (Boolean, String?) -> Unit)
    suspend fun logOut()
    suspend fun isLoggedIn(): Boolean
}