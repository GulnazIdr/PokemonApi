package com.example.pokemon.data

import android.util.Log
import com.example.pokemon.data.pokemon.Pokemon
import com.example.pokemon.data.pokemon.PokemonInfo
import com.example.pokemon.data.pokemon.PokemonList
import com.example.pokemon.domain.FetchResult
import com.example.pokemon.domain.LocalMapper
import com.example.pokemon.domain.PokemonRepository
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val api: PokemonAPI
): PokemonRepository, LocalMapper() {
    override suspend fun fetchPokemonList(limit: Int, offset: Int) : FetchResult<PokemonList>{
        return try {
            val data = api.fetchPokemonList(limit, offset)
            FetchResult.Success(data)
        }catch (e: Exception){
            FetchResult.Error("${e.message}")
        }
    }

    override suspend fun fetchPokemon(name: String): FetchResult<PokemonInfo> {
        return try {
            val data = api.fetchPokemon(name)
            FetchResult.Success(data)
        }catch (e: Exception){
            Log.e("FETCH CONTENT", "${e::class.simpleName} ${e.message}")
            FetchResult.Error("${e.message}")
        }
    }

    override suspend fun searchPokemonName(name: String): FetchResult<List<Pokemon>> {
        return try {
            var list: List<PokemonInfo> = listOf(api.searchPokemon(name))
            FetchResult.Success(list.map { it.toPokemon() })
        } catch (e: Exception){
            FetchResult.Error("${e.message}")
        }
    }
}