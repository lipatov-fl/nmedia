package ru.netology.nmedia

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.databinding.CardPostBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var bindingClass: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        viewModel.data.observe(this) { posts ->
            bindingClass.lists.removeAllViews()
            posts.forEach { post ->
                CardPostBinding.inflate(layoutInflater, bindingClass.lists, true).apply {
                    author.text = post.author
                    published.text = post.published
                    content.text = post.content
                    share.text = longNumbersToString(post.share)
                    like.text = longNumbersToString(post.likes)
                    visibility.text = longNumbersToString(post.visibility)
                    imageLike.setImageResource(if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_baseline_favorite_border_24)
                    imageLike.setOnClickListener {
                        viewModel.likeById(post.id)
                    }
                    imageShare.setOnClickListener {
                        viewModel.share(post.share)
                    }
                }
            }
        }
    }

    private fun longNumbersToString(number: Long): String {
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