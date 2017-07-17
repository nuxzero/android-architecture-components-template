package com.example.nux.news.data

import com.example.nux.news.data.models.News
import io.reactivex.Observable

interface NewsRepositoryDataSource {

    fun loadNewses() : Observable<List<News>>

}