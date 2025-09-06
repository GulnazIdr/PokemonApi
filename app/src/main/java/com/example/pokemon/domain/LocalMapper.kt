package com.example.pokemon.domain

import com.example.pokemon.data.pokemon.Pokemon
import com.example.pokemon.data.pokemon.PokemonInfo
import com.example.pokemon.data.room.PokemonEntity

abstract class LocalMapper {
    protected fun PokemonEntity.toPokemon(): Pokemon{
        return Pokemon(
            id = id,
            name = name,
            image = image
        )
    }

    protected fun Pokemon.toEntityPokemon(): PokemonEntity{
        return PokemonEntity(
            id = id,
            name = name,
            image = image
        )
    }

    protected fun PokemonInfo.toPokemon(): Pokemon{
        return Pokemon(
            id = id,
            name = name,
            image =  "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png"
        )
    }
}