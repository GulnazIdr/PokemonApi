package com.example.pokemon.data.room

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_info", indices = [
    Index("id", unique = true),
    Index("name", unique = true),
    Index("image", unique = true)
])
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false) val id: Int = 0,
    val name: String,
    val image: String
)
