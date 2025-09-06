package com.example.pokemon

import kotlinx.serialization.Serializable

interface Destination

@Serializable
data object Home: Destination

@Serializable
data class PokemonDetails(val name: String): Destination