package com.example.pokemon.presentation.common

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.data.pokemon.Pokemon
import com.example.pokemon.data.pokemon.PokemonList
import com.example.pokemon.domain.CachedPokemonRepository
import com.example.pokemon.domain.FetchResult
import com.example.pokemon.domain.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewmodel @Inject constructor(
    private val pokemonRepository: PokemonRepository,
    private val cachedPokemonRepository: CachedPokemonRepository
): ViewModel() {

    private var currentPage = 0
    var isPageEnded = mutableStateOf(false)
    var isLoading = mutableStateOf(false)
    var errorMessage = mutableStateOf("")
    var isCachingData = mutableStateOf(false)

    private val _isSearching = mutableStateOf(false)
    val isSearching get() = _isSearching.value

    private val _pokemonList = mutableStateOf<List<Pokemon>>(listOf())
    val pokemonList get() = _pokemonList.value

    private val _remoteSearchedPokemonList = mutableStateOf<List<Pokemon>>(listOf())
    val remoteSearchedPokemonList get() = _remoteSearchedPokemonList.value

    init {
        fetchPokemonList()
    }

    fun fetchPokemonList() = viewModelScope.launch {
        isLoading.value = true
        val res = pokemonRepository.fetchPokemonList(20, currentPage*20)

        when(res){
            is FetchResult.Success<PokemonList> -> {
                isPageEnded.value = currentPage * 20 >= res.data!!.count

                errorMessage.value = ""
                val fetched = res.data.results.map { result ->
                    val id = if (result.url.endsWith("/"))
                        result.url.dropLast(1).takeLastWhile { it.isDigit() }
                    else result.url.takeLastWhile { it.isDigit() }

                    val img =
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"

                    Pokemon(
                        id = id.toInt(),
                        name = result.name,
                        image = img
                    )

                }
                _pokemonList.value += fetched
                currentPage++
                isLoading.value = false
                cachedPokemonRepository.setCachedPokemonList(_pokemonList.value.dropLast(20))
            }
            is FetchResult.Error<PokemonList> -> {
                isCachingData.value = true
                isLoading.value = false
                errorMessage.value = "Something went wrong. Try again later."
                try {
                    _pokemonList.value = emptyList()
                    _pokemonList.value += cachedPokemonRepository.getCachedPokemonList()
                }catch (e: Exception) {
                    Log.e("FETCH CACHED DATA", "${e::class.simpleName} ${e.message}")
                }
            }
        }
    }

    fun searchPokemon(pokemonName: String) {
        viewModelScope.launch(Dispatchers.Default) {
            if(pokemonName.isEmpty()){
                _isSearching.value = false
                return@launch
            }

            _remoteSearchedPokemonList.value = emptyList<Pokemon>()
            val res = pokemonRepository.searchPokemonName(pokemonName.trim().toLowerCase())
            when (res) {
                is FetchResult.Success<List<Pokemon>> -> {
                    _remoteSearchedPokemonList.value = res.data ?: _pokemonList.value
                }
                is FetchResult.Error<List<Pokemon>> -> {
                    if (isCachingData.value) {
                        _remoteSearchedPokemonList.value = _pokemonList.value.filter {
                            it.name.contains(pokemonName.trim(), ignoreCase = true)
                        }
                    }
                }
            }
            _isSearching.value = true
        }
    }

    fun fetchPokemonInfo(name: String) = viewModelScope.launch {

    }
}