package com.example.flowtechticstask.data.repository.mappers

import com.example.flowtechticstask.data.repository.models.CharactersDto
import com.example.flowtechticstask.domain.models.Character
import com.example.flowtechticstask.domain.models.CharactersPaging

fun CharactersDto.toDomain(): CharactersPaging = CharactersPaging(
    pages = info.pages,
    characters = results.map { it.toDomain() }
)

private fun CharactersDto.Result.toDomain(): Character =
    Character(
        id = id,
        name = name,
        status = status,
        species = species,
        image = image
    )
