package com.example.nux.news.newslist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.nux.news.data.NewsRepository
import com.example.nux.news.data.models.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class NewsListViewModel(val repository: NewsRepository) : ViewModel() {

    fun loadNewsList(): LiveData<List<News>> {
        val liveData = MutableLiveData<List<News>>()

        repository.loadNewses()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ newsList: List<News> ->
                    liveData.value = newsList
                })

        return liveData
    }

}
