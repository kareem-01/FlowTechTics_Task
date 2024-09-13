package com.example.flowtechticstask.data.repository.repository

import com.example.flowtechticstask.data.repository.datasource.CharactersDataSource
import com.example.flowtechticstask.data.repository.mappers.toDomain
import com.example.flowtechticstask.domain.models.CharactersPaging
import com.example.flowtechticstask.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val dataSource: CharactersDataSource,
) : CharactersRepository {
    override suspend fun getAllCharacters(page: Int): CharactersPaging {
        return dataSource.getAllCharacters(page).toDomain()
    }
}