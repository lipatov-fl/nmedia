package ru.netology.nmedia.viewmodel

import androidx.lifecycle.ViewModel
import ru.netology.nmedia.repository.PostRepository
import ru.netology.nmedia.repository.PostRepositoryInMemoryImpl

class PostViewModel : ViewModel() {
    private val repository: PostRepository = PostRepositoryInMemoryImpl()
    val data = repository.getAll()
    fun likeById(id: Long) = repository.likeById(id)
    fun share(share: Long) = repository.share(share)

    fun longNumbersToString(number: Long): String {
        return when {
            number < 1000 -> number.toString()
            number in 1000..9999 -> "1." + (number % 1000 / 100).toString() + "K"
            number in 10000..999999 ->
                (number / 10000).toString() + "." + (number % 10000 / 1000).toString() + "K"

            else ->
                (number / 1000000).toString() + "." + (number % 1000000 / 100000).toString() + "M"
        }
    }
}

