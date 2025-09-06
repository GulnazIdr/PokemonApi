package com.example.pokemon.di

import android.content.Context
import com.example.pokemon.data.PokemonAPI
import com.example.pokemon.data.room.PokemonDao
import com.example.pokemon.data.room.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): PokemonDatabase{
        return PokemonDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideDao(pokemonDatabase: PokemonDatabase): PokemonDao{
        return pokemonDatabase.getPokemonDao()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): PokemonAPI{
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokemonAPI::class.java)
    }
}