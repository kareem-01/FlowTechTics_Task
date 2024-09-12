package com.example.flowtechticstask.di

import com.example.flowtechticstask.utils.StringsProvider
import com.example.flowtechticstask.utils.StringsProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class StringProviderModule {
    @Binds
    @ViewModelScoped
    abstract fun provideStringResource(stringsProvider: StringsProviderImpl): StringsProvider
}