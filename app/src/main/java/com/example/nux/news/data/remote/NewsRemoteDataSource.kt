package com.example.nux.news.data.remote

import com.example.nux.news.data.models.News
import io.reactivex.Observable

interface NewsRemoteDataSource {

    fun loadNewses(): Observable<List<News>>

}