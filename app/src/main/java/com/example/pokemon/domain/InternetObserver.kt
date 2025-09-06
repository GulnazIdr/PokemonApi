package com.example.pokemon.domain

import kotlinx.coroutines.flow.Flow

interface InternetObserver {
    val isConnected: Flow<Boolean>
}