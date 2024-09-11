package com.example.flowtechticstask.di

import com.example.flowtechticstask.data.remote.dataSource.AuthDataSourceImpl
import com.example.flowtechticstask.data.remote.dataSource.CharactersDataSourceImpl
import com.example.flowtechticstask.data.repository.datasource.AuthDataSource
import com.example.flowtechticstask.data.repository.datasource.CharactersDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Singleton
    @Binds
   abstract fun bindAuthDataSource(authDataSourceImpl: AuthDataSourceImpl): AuthDataSource

   @Singleton
   @Binds
   abstract fun bindCharactersDataSource(characterDataSourceImpl: CharactersDataSourceImpl): CharactersDataSource
}