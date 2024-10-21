package com.example.data.entity

data class PostEntity(
    val id:Long,
    val writer:String,
    val title:String,
    val content:String,
    val isLoved:Boolean
)