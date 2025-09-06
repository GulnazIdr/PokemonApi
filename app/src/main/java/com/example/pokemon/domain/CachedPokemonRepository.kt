package com.example.pokemon.domain

import android.util.Log
import com.example.pokemon.data.pokemon.Pokemon
import com.example.pokemon.data.room.PokemonDao
import javax.inject.Inject

class CachedPokemonRepository @Inject constructor(
    private val pokemonDao: PokemonDao
): LocalMapper() {

    suspend fun setCachedPokemonList(pokemonList: List<Pokemon>){
        try {
            pokemonDao.setCachedPokemon(pokemonList.map { it.toEntityPokemon() })
        }catch (e: Exception){
            Log.e("FETCH CONTENT", "${e::class.simpleName} ${e.message}")
        }
    }

    suspend fun getCachedPokemonList(): List<Pokemon>{
        return try {
            pokemonDao.getCachedPokemon().map { it.toPokemon() }
        }catch (e: Exception){
            Log.e("FETCH CONTENT", "${e::class.simpleName} ${e.message}")
            emptyList<Pokemon>()
        }
    }
}