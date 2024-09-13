package com.example.flowtechticstask.di

import com.example.flowtechticstask.data.repository.repository.AuthRepositoryImpl
import com.example.flowtechticstask.data.repository.repository.CharactersRepositoryImpl
import com.example.flowtechticstask.domain.repository.AuthRepository
import com.example.flowtechticstask.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl,
    ): AuthRepository

    @Singleton
    @Binds
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharactersRepositoryImpl,
    ): CharactersRepository
}