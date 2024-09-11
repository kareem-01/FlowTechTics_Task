package com.example.flowtechticstask.data.repository.datasource

interface AuthDataSource {
    suspend fun signUp()
    suspend fun logIn()

}