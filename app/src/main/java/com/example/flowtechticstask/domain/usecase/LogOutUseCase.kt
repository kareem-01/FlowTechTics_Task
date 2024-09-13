package com.example.flowtechticstask.domain.usecase

import com.example.flowtechticstask.domain.repository.AuthRepository
import javax.inject.Inject

class LogOutUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke() = authRepository.logOut()
}