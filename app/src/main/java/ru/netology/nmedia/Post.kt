package ru.netology.nmedia

data class Post(
    val id: Int,
    val published: String,
    val content: String,
    val author: String,
    val likes: Long,
    val share: Long,
    val visibility: Long,
    val likedByMe: Boolean = false
)
