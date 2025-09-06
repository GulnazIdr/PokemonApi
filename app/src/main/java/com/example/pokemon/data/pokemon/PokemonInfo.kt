package com.example.pokemon.data.pokemon

data class PokemonInfo(
    val abilities: List<Ability>,
    val forms: List<Form>,
    val height: Int,
    val id: Int,
    val moves: List<Move>,
    val name: String,
    val order: Int,
    val species: Species,
    val types: List<Type>,
    val weight: Int
)