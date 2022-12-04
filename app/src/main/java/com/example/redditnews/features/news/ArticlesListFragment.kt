package com.example.redditnews.features.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.presentation.news.MainContract
import com.example.presentation.news.MainViewModel
import com.example.redditnews.databinding.FragmentArticlesListBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArticlesListFragment : Fragment() {

    @Inject
    lateinit var viewModel: MainViewModel

    private lateinit var binding: FragmentArticlesListBinding

    private lateinit var adapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentArticlesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = NewsAdapter {

        }

        binding.articlesRv.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        viewModel.setIntent(MainContract.MainIntent)
        render()
    }

    private fun render() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect {
                when (it) {
                    is MainContract.MainViewState.Idle -> {
                        binding.progressBar.visibility = GONE
                    }
                    is MainContract.MainViewState.Loading -> {
                        binding.progressBar.visibility = VISIBLE
                        binding.articlesRv.visibility = GONE
                    }
                    is MainContract.MainViewState.Success -> {
                        binding.progressBar.visibility = GONE
                        binding.articlesRv.visibility = VISIBLE
                        adapter.submitList(it.newsList)
                    }
                    is MainContract.MainViewState.Error -> {
                        binding.progressBar.visibility = GONE
                        binding.articlesRv.visibility = GONE
                        Toast.makeText(context, "Error, couldn't get data :( \n Try again later!", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }
}