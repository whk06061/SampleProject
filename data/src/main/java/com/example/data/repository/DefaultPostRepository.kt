package com.example.data.repository

import com.example.data.entity.getResult
import com.example.data.mapper.toDomain
import com.example.data.service.PostService
import com.example.domain.model.Result
import com.example.domain.repository.PostRepository
import com.example.domain.model.Post
import kotlinx.coroutines.flow.Flow

class DefaultPostRepository(
    private val postService: PostService
): PostRepository {
    override fun getPosts(): Flow<Result<List<Post>>> {
        return getResult(
            execute = { postService.getPosts() },
            mapper = { toDomain() }
        )
    }
}