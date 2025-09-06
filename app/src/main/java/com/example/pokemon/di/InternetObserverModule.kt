package com.example.pokemon.di

import com.example.pokemon.domain.InternetConnectionObserver
import com.example.pokemon.domain.InternetObserver
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class InternetObserverModule {
    @Binds
    @Singleton
    abstract fun provideObserver(internetConnectionObserver: InternetConnectionObserver)
    : InternetObserver
}