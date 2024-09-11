package com.example.flowtechticstask.data.remote.dataSource

import com.example.flowtechticstask.data.repository.datasource.AuthDataSource
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthDataSource {
    override suspend fun signUp() {
        TODO("Not yet implemented")
    }

    override suspend fun logIn() {
        TODO("Not yet implemented")
    }


}