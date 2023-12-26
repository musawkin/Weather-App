package com.example.weather

sealed class AlbumState {
    data class Success(var albumState: ResponseModel?): AlbumState()
    data class Error(var message: String?): AlbumState()
    object Loading: AlbumState()
}