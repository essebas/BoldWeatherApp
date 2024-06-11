package com.sebasdev.boldweatherapp.search.presentation.state

sealed class SearchState<out T> {
    data object Idle : SearchState<Nothing>()
    data object Loading : SearchState<Nothing>()
    data class Success<T>(val data: T) : SearchState<T>()
    data class Error(val message: String) : SearchState<Nothing>()
}