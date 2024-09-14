package com.example.flowtechticstask.data.remote.dataSource


import com.example.flowtechticstask.data.repository.datasource.AuthDataSource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthDataSourceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthDataSource {

    override suspend fun signUp(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) = suspendCancellableCoroutine { continuation ->
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                    continuation.resume(Unit)
                } else {
                    val message = when (task.exception) {
                        is FirebaseAuthUserCollisionException -> "User already exists"
                        else -> task.exception?.message
                    }
                    onResult(false, message)
                    continuation.resumeWithException(task.exception ?: Exception("Unknown error"))
                }
            }
    }

    override suspend fun logIn(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) = suspendCancellableCoroutine { continuation ->
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onResult(true, null)
                    continuation.resume(Unit)
                } else {
                    onResult(false, task.exception?.message)
                    continuation.resumeWithException(task.exception ?: Exception("Unknown error"))
                }
            }
    }

    override suspend fun logOut() {
        firebaseAuth.signOut()
    }

    override suspend fun isLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}
