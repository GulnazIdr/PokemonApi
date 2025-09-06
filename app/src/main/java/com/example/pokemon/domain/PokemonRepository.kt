package com.example.pokemon.domain

import com.example.pokemon.data.pokemon.Pokemon
import com.example.pokemon.data.pokemon.PokemonInfo
import com.example.pokemon.data.pokemon.PokemonList

interface PokemonRepository {
    suspend fun fetchPokemonList(limit: Int, offset: Int) : FetchResult<PokemonList>
    suspend fun fetchPokemon(name: String): FetchResult<PokemonInfo>
    suspend fun searchPokemonName(name: String): FetchResult<List<Pokemon>>
}