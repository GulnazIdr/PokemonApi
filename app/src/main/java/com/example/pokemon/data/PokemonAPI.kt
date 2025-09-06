package com.example.pokemon.data

import com.example.pokemon.data.pokemon.PokemonInfo
import com.example.pokemon.data.pokemon.PokemonList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonAPI {

    @GET("pokemon")
    suspend fun fetchPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
    ): PokemonList

    @GET("pokemon/{name}")
    suspend fun fetchPokemon(@Path("name") name: String): PokemonInfo

    @GET("pokemon/{name}")
    suspend fun searchPokemon(@Path("name") name: String): PokemonInfo
}