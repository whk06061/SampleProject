package com.example.domain.model

sealed class AppError {
    data class ClientError(val message: String) : AppError()
    data class Unauthorized(val message: String) : AppError()
    data class ServerError(val message: String) : AppError()
    data class NetworkError(val message: String) : AppError()
    data class UnknownError(val message: String) : AppError()
}