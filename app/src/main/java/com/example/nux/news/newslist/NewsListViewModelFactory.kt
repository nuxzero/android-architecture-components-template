package com.example.nux.news.newslist

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.nux.news.data.NewsRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NewsListViewModelFactory @Inject constructor(val repository: NewsRepository) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        return NewsListViewModel(repository) as T
    }

}
