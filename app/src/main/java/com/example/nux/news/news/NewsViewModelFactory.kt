package com.example.nux.news.news

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.example.nux.news.data.NewsRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NewsViewModelFactory @Inject constructor(val repository: NewsRepository) :
        ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>?): T {
        return NewsViewModel(repository) as T
    }

}
