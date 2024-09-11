package com.example.flowtechticstask.domain.repository

import com.example.flowtechticstask.domain.models.Character

interface CharactersRepository {
    suspend fun getAllCharacters():List<Character>
}