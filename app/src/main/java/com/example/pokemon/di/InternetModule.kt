package com.example.pokemon.di

import android.content.Context
import com.example.pokemon.domain.InternetConnectionObserver
import com.example.pokemon.domain.InternetObserver
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InternetModule {

    @Provides
    @Singleton
    fun provideInternetModule(@ApplicationContext context: Context): InternetConnectionObserver{
        return InternetConnectionObserver(context)
    }
}