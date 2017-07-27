package com.example.nux.news.data.local

import com.example.nux.news.data.models.News
import io.reactivex.Flowable

interface NewsLocalDataSource {

    fun loadNewsList(): List<News>

    fun loadNews(title: String): Flowable<News>

    fun saveNewsList(newsList: List<News>)

}
