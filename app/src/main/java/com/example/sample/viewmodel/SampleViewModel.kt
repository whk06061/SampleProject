package com.example.sample.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.domain.model.AppError
import com.example.domain.usecase.GetPostUseCase
import kotlinx.coroutines.launch

class SampleViewModel(private val getPostUseCase: GetPostUseCase) : BaseViewModel() {
    fun getPost() {
        viewModelScope.launch {
            async(
                operation = {
                    getPostUseCase()
                },
                onSuccess = {

                },
                onFailure = {
                    // 에러 타입에 대해 처리
                    when (it) {
                        is AppError.NetworkError -> TODO()
                        is AppError.ServerError -> TODO()
                        is AppError.ClientError -> TODO()
                        is AppError.Unauthorized -> TODO()
                        is AppError.UnknownError -> TODO()
                    }
                }
            )

        }
    }
}