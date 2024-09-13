package com.example.flowtechticstask.domain.usecase

import com.example.flowtechticstask.domain.repository.AuthRepository
import javax.inject.Inject

class LogInUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) {
        authRepository.logIn(email, password, onResult)
    }
}