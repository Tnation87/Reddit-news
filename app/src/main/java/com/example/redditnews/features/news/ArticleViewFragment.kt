package com.example.redditnews.features.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.presentation.news.ViewArticleContract
import com.example.presentation.news.ViewArticleViewModel
import com.example.redditnews.common.extensions.loadImageUrl
import com.example.redditnews.databinding.FragmentArticleViewBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticleViewFragment : Fragment() {

    @Inject
    lateinit var viewModel: ViewArticleViewModel

    private lateinit var binding: FragmentArticleViewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticleViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backIb.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.setIntent(ViewArticleContract.GetArticleIntent)
        render()
    }

    private fun render() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it) {
                    is ViewArticleContract.ViewArticleState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        showErrorToast()
                    }
                    is ViewArticleContract.ViewArticleState.Idle -> {
                        binding.progressBar.visibility = View.GONE
                    }
                    is ViewArticleContract.ViewArticleState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is ViewArticleContract.ViewArticleState.Success -> {
                        binding.progressBar.visibility = View.GONE
                        binding.articleTitleTv.visibility = View.GONE
                        binding.thumbnailIv.visibility = View.GONE
                        binding.bodyTv.visibility = View.GONE

                        it.article.title?.let { title ->
                            binding.articleTitleTv.text = title
                            binding.articleTitleTv.visibility = View.VISIBLE
                        }

                        it.article.body?.let { body ->
                            binding.bodyTv.text = body
                            binding.bodyTv.visibility = View.VISIBLE
                        }

                        it.article.thumbnail?.let { thumbnail ->
                            binding.thumbnailIv.loadImageUrl(thumbnail)
                            binding.thumbnailIv.visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private fun showErrorToast() = Toast.makeText(context, "Error, couldn't get data :( \n Try again later!", Toast.LENGTH_LONG).show()

}