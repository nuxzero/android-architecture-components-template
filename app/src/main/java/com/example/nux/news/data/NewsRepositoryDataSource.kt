package com.example.nux.news.data

import com.example.nux.news.data.models.News
import io.reactivex.Flowable
import io.reactivex.Observable

interface NewsRepositoryDataSource {

    fun loadNewsList(): Observable<List<News>>

    fun loadNews(title: String): Flowable<News>

}