package com.example.data.entity

import com.example.domain.model.Result
import com.example.domain.model.AppError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

data class ApiResponse<T>(
    val isSuccess: Boolean,
    val code: Int,
    val message: String,
    val result: T
)

fun <T, R> getResult(
    execute: () -> Response<ApiResponse<T>>,
    mapper: T.() -> R
): Flow<Result<R>> = flow {
    emit(Result.Loading)
    emit(getHttpResult(execute, mapper))
}

private fun <T, R> getHttpResult(
    executeApi: () -> Response<ApiResponse<T>>,
    mapper: T.() -> R
): Result<R> {
    val response = runCatching {
        executeApi()
    }.getOrElse {
        return Result.Error(AppError.NetworkError("Network Error"))
    }

    val responseBody = response.body()
    if (responseBody?.isSuccess == true) {
        return Result.Success(responseBody.result.mapper())
    } else {
        handleHttpError(responseBody?.code ?: response.code(), responseBody?.message ?: response.message())
    }
    return Result.Error(AppError.UnknownError(response.message()))
}

private fun handleHttpError(code: Int?, message: String): Result.Error {
    return when (code) {
        401 -> Result.Error(AppError.Unauthorized(message))
        400, 403, 404, 405 -> Result.Error(AppError.ClientError(message))
        500, 502, 503, 504 -> Result.Error(AppError.ServerError(message))
        else -> Result.Error(AppError.UnknownError(message))
    }
}