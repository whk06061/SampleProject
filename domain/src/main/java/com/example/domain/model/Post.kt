package com.example.domain.model

data class Post(
    val id:Long,
    val writer:String,
    val title:String,
    val content:String,
    val isLoved:Boolean
)