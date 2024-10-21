package com.example.sample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AppError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import com.example.domain.model.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel:ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    fun <T> async(
        operation: suspend () -> Flow<Result<T>>,
        onSuccess: (T) -> Unit,
        onFailure: (AppError) -> Unit
    ) {
        viewModelScope.launch {
            operation().collect { result ->
                when (result) {
                    is Result.Loading -> _isLoading.value = true
                    is Result.Success -> {
                        _isLoading.value = false
                        onSuccess(result.data)
                    }

                    is Result.Error -> {
                        _isLoading.value = false
                        onFailure(result.error)
                    }
                }
            }
        }
    }
}