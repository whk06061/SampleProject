package com.example.data.mapper

import com.example.data.entity.PostEntity
import com.example.domain.model.Post

fun Post.toEntity() = PostEntity(
    id = this.id,
    writer = this.writer,
    title = this.title,
    content = this.content,
    isLoved = this.isLoved
)

fun PostEntity.toDomain() = Post(
    id = this.id,
    writer = this.writer,
    title = this.title,
    content = this.content,
    isLoved = this.isLoved
)

fun List<PostEntity>.toDomain() = this.map { it.toDomain() }