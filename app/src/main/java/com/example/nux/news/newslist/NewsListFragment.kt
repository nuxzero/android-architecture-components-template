package com.example.nux.news.newslist

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.nux.news.R
import com.example.nux.news.app.NewsApplication
import com.example.nux.news.data.models.News
import com.example.nux.news.databinding.NewsListFragmentBinding
import javax.inject.Inject

class NewsListFragment : LifecycleFragment() {

    @Inject
    lateinit var viewModelFactory: NewsListViewModelFactory

    private lateinit var newsListViewModel: NewsListViewModel

    private lateinit var binding: NewsListFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.news_list_fragment, container,
                false)

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
        newsListViewModel.loadNewsList().observe(this,
                Observer<List<News>> { newsList ->
                    Log.i("NewsList", "${newsList?.size}")
                })
    }

}
