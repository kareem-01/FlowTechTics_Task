package com.example.flowtechticstask.data.repository.repository

import com.example.flowtechticstask.data.repository.datasource.AuthDataSource
import com.example.flowtechticstask.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val dataSource: AuthDataSource,
) : AuthRepository {
    override suspend fun signUp(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) =
        dataSource.signUp(email, password, onResult)


    override suspend fun logIn(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) = dataSource.logIn(email, password, onResult)


    override suspend fun logOut() = dataSource.logOut()


    override suspend fun isLoggedIn(): Boolean = dataSource.isLoggedIn()

}