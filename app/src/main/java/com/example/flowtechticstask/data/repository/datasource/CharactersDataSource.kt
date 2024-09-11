package com.example.flowtechticstask.data.repository.datasource

import com.example.flowtechticstask.data.repository.models.CharactersDto

interface CharactersDataSource {
    suspend fun getAllCharacters(): CharactersDto
}