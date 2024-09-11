package com.example.flowtechticstask.data.remote.dataSource

import com.example.flowtechticstask.data.remote.service.CharactersService
import com.example.flowtechticstask.data.repository.datasource.CharactersDataSource
import com.example.flowtechticstask.data.repository.models.CharactersDto
import javax.inject.Inject

class CharactersDataSourceImpl @Inject constructor(
    private val charactersService: CharactersService,
) : CharactersDataSource {
    override suspend fun getAllCharacters(): CharactersDto {
        TODO("Not yet implemented")
    }
}