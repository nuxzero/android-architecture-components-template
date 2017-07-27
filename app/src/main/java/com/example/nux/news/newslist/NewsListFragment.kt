package com.example.nux.news.newslist

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nux.news.R
import com.example.nux.news.app.NewsApplication
import com.example.nux.news.data.models.News
import com.example.nux.news.databinding.NewsItemBinding
import com.example.nux.news.databinding.NewsListFragmentBinding
import com.example.nux.news.news.NewsActivity
import com.example.nux.news.news.NewsArgument
import javax.inject.Inject

class NewsListFragment : LifecycleFragment() {

    @Inject
    lateinit var viewModelFactory: NewsListViewModelFactory

    private lateinit var newsListViewModel: NewsListViewModel

    private lateinit var binding: NewsListFragmentBinding

    private val newsClickListener = object : NewsClickListener {
        override fun onClick(news: News) {
            val intent = Intent(context, NewsActivity::class.java)
            intent.putExtra(NewsArgument.ARG_TITLE, news.title)
            activity.startActivity(intent)
        }
    }

    private var adapter = NewsAdapter(newsClickListener)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_list_fragment, container,
                false)
        binding.newsList.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        DaggerNewsListViewModelComponent.builder()
                .newsRepositoryComponent((activity.application as NewsApplication).newsRepositoryComponent)
                .build()
                .inject(this)

        newsListViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewsListViewModel::class.java)

        subscribeUi()
    }

    fun subscribeUi() {
        binding.isLoading = true
        newsListViewModel.loadNewsList().observe(this,
                Observer<List<News>> { newsList ->
                    binding.isLoading = false
                    adapter.newsList = newsList
                })
    }

    private class NewsAdapter(val listener: NewsClickListener) :
            RecyclerView.Adapter<NewsAdapter.NewsItemViewHolder>() {

        var newsList: List<News>? = null
            set(value) {
                field = value
                notifyDataSetChanged()
            }

        override fun getItemCount(): Int {
            return newsList?.size ?: 0
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsItemViewHolder {
            val newsItemBinding = DataBindingUtil.inflate<NewsItemBinding>(LayoutInflater.from
            (parent?.context), R.layout.news_item, parent, false)
            newsItemBinding.listener = listener
            return NewsItemViewHolder(newsItemBinding)
        }

        override fun onBindViewHolder(holder: NewsItemViewHolder?, position: Int) {
            val news = newsList?.get(position)
            if (news != null) {
                holder?.bind(news)
            }
        }

        class NewsItemViewHolder(val binding: NewsItemBinding) : RecyclerView
        .ViewHolder(binding.root) {

            fun bind(news: News) {
                binding.news = news
            }

        }

    }

}
