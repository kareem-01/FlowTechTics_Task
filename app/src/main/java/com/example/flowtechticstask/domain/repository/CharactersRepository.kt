package com.example.flowtechticstask.domain.repository

import com.example.flowtechticstask.domain.models.CharactersPaging

interface CharactersRepository {
    suspend fun getAllCharacters(page: Int): CharactersPaging
}