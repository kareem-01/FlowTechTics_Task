package com.example.flowtechticstask.domain.usecase

import com.example.flowtechticstask.domain.models.CharactersPaging
import com.example.flowtechticstask.domain.repository.CharactersRepository
import javax.inject.Inject

class GetAllCharactersUseCase @Inject constructor(private val repository: CharactersRepository) {
    suspend operator fun invoke(page: Int): CharactersPaging = repository.getAllCharacters(page)
}