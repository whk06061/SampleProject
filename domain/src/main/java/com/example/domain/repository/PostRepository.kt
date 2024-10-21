package com.example.domain.repository

import com.example.domain.model.Result
import com.example.domain.model.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    fun getPosts(): Flow<Result<List<Post>>>
}