package com.example.domain.usecase

import com.example.domain.model.Post
import com.example.domain.model.Result
import com.example.domain.repository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostUseCase(
    private val postRepository: PostRepository,
    // 또 다른 레포지토리,,,,
) {
    operator fun invoke(): Flow<Result<List<Post>>> {
        // 두 개의 레
        return postRepository.getPosts()
    }
}