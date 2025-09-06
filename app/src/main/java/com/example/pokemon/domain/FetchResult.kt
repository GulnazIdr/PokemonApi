package com.example.pokemon.domain

sealed class FetchResult <T> (val data: T? = null, val message: String? = null) {
    class Success <T> (data: T):  FetchResult<T> (data)
    class Error <T> (message: String):FetchResult<T> (message = message)
}