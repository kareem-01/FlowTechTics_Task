package com.example.flowtechticstask.domain.usecase

import com.example.flowtechticstask.domain.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        onResult: (Boolean, String?) -> Unit,
    ) = authRepository.signUp(email, password, onResult)
}