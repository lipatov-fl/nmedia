package ru.netology.nmedia

data class Post(
    val id: Int,
    val published: String,
    val content: String,
    val author: String,
    val likes: Long,
    val share: Long = 998,
    val visibility: Long = 1,
    val likedByMe: Boolean = false
)
