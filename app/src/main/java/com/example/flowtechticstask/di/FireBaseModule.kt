package com.example.flowtechticstask.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object FireBaseModule {
    @Singleton
    @Provides
    fun provideFireStore() = Firebase.firestore

    @Singleton
    @Provides
    fun provideFireBaseAuth() = Firebase.auth
}