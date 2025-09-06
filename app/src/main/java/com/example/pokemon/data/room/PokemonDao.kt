package com.example.pokemon.data.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface PokemonDao {
    @Upsert
    suspend fun setCachedPokemon(pokemonEntity: List<PokemonEntity>)

    @Query("SELECT * FROM pokemon_info")
    suspend fun getCachedPokemon(): List<PokemonEntity>
}