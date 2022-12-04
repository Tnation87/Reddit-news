package com.example.redditnews.features.news

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.presentation.models.NewsUiModel
import com.example.redditnews.databinding.ArticleItemLayoutBinding

class NewsAdapter(private val clickListener: (NewsUiModel) -> Unit):
    ListAdapter<NewsUiModel, NewsAdapter.ViewHolder>(NewsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ArticleItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    class ViewHolder(private val binding: ArticleItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: NewsUiModel, clickListener: (NewsUiModel) -> Unit) {
            binding.articleTitleTv.visibility = GONE
            binding.thumbnailIv.visibility = GONE
            binding.separator.visibility = GONE

            article.title?.let {
                binding.articleTitleTv.visibility = VISIBLE
                binding.separator.visibility = VISIBLE
                binding.articleTitleTv.text = it
            }

            article.thumbnail?.let {
                binding.thumbnailIv.visibility = VISIBLE
                binding.separator.visibility = VISIBLE
                val builder = Glide.with(binding.thumbnailIv).load(it).apply(RequestOptions().centerInside())
                builder.into(binding.thumbnailIv)
            }

            itemView.setOnClickListener {
                clickListener(article)
            }
        }
    }

    class NewsDiffCallback : DiffUtil.ItemCallback<NewsUiModel>() {
        override fun areItemsTheSame(
            oldItem: NewsUiModel,
            newItem: NewsUiModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: NewsUiModel,
            newItem: NewsUiModel
        ): Boolean {
            return oldItem == newItem
        }
    }

}