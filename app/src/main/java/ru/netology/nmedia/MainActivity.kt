package ru.netology.nmedia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import ru.netology.nmedia.databinding.ActivityMainBinding
import ru.netology.nmedia.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    val viewModel: PostViewModel by viewModels()
    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        viewModel.data.observe(this) { post ->
            bindingClass.apply {
                author.text = post.author
                published.text = post.published
                content.text = post.content
                share.text = longNumbersToString(post.share)
                like.text = longNumbersToString(post.likes)
                visibility.text = longNumbersToString(post.visibility)
                if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_baseline_favorite_border_24
            }
        }
        setupListeners()
    }
    private fun setupListeners() {
        bindingClass.apply {
            imageLike.setOnClickListener {
                viewModel.like()
                like.text = longNumbersToString(post.likes)
            }

            imageShare.setOnClickListener {
                imageShare.setImageResource(R.drawable.ic_sharebyme_24)
                post.share++
                share.text = longNumbersToString(post.share)
            }
        }
    }
}