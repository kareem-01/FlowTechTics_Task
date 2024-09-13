package com.example.flowtechticstask.data.remote.service

import com.example.flowtechticstask.data.repository.models.CharactersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersService {
    @GET("character")
    suspend fun getAllCharacters(@Query("page") page: Int): Response<CharactersDto>
}