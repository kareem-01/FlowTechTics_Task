package com.example.flowtechticstask.presentation.home

import com.example.flowtechticstask.domain.models.Character

fun Character.toUiModel(): CharacterUiModel = CharacterUiModel(
    id = id,
    name = name,
    status = status,
    species = species,
    imageUrl = image
)

fun List<Character>.toUiModel(): List<CharacterUiModel> = map { it.toUiModel() }