package com.example.flowtechticstask.data.remote.dataSource

import com.example.flowtechticstask.data.repository.datasource.AuthDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import javax.inject.Inject

class AuthDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthDataSource {
    override suspend fun signUp(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, null)
            } else {
                val message = when (task.exception) {
                    is FirebaseAuthUserCollisionException -> "User already exists"
                    else -> task.exception?.message
                }
                onResult(false, message)
            }
        }
    }

    override suspend fun logIn(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, null)
            } else {
                onResult(false, task.exception?.message)
            }
        }
    }

    override suspend fun logOut() {
        firebaseAuth.signOut()
    }

    override suspend fun isLoggedIn(): Boolean {
        firebaseAuth.currentUser?.let {
            return true
        }
        return false
    }


}