package com.example.flowtechticstask.data.remote.service

import com.example.flowtechticstask.data.repository.models.CharactersDto
import retrofit2.Response
import retrofit2.http.GET

interface CharactersService {
    @GET("character")
    suspend fun getAllCharacters(): Response<CharactersDto>
}