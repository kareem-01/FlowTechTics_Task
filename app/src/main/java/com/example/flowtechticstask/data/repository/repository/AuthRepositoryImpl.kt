package com.example.flowtechticstask.data.repository.repository

import com.example.flowtechticstask.data.repository.datasource.AuthDataSource
import com.example.flowtechticstask.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun signUp() {
        TODO("Not yet implemented")
    }

    override suspend fun logIn() {
        TODO("Not yet implemented")
    }

}