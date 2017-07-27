package com.example.nux.news.news

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.nux.news.data.NewsRepository
import com.example.nux.news.data.models.News
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsViewModel(val repository: NewsRepository) : ViewModel() {

    fun loadNews(title: String): LiveData<News> {
        val liveData = MutableLiveData<News>()
        repository.loadNews(title)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ news -> liveData.value = news })

        return liveData
    }

}
