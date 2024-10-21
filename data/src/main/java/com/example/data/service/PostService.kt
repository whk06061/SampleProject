package com.example.data.service

import com.example.data.entity.ApiResponse
import com.example.data.entity.PostEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST

interface PostService {
    @GET
    fun getPosts(): Response<ApiResponse<List<PostEntity>>>
}