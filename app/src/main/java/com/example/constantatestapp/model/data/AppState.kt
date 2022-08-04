package com.example.constantatestapp.model.data

sealed class AppState {
    data class Success(val data: DataModel?) : AppState()
    class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}