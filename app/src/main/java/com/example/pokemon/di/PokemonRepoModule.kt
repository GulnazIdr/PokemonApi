package com.example.pokemon.di

import com.example.pokemon.data.PokemonAPI
import com.example.pokemon.data.PokemonRepositoryImpl
import com.example.pokemon.domain.PokemonRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonRepoModule {

    @Provides
    @Singleton
    fun providePokemonRepo(pokemonAPI: PokemonAPI): PokemonRepository{
        return PokemonRepositoryImpl(pokemonAPI)
    }
}