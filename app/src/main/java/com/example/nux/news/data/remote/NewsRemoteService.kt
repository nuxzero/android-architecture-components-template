package com.example.nux.news.data.remote

import com.example.nux.news.data.models.NewsesResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface NewsRemoteService {

    @GET("articles?source=techcrunch&sortBy=latest&apiKey=691700b86fee41c58f6cee530e91623b")
    fun getNewses(): Observable<NewsesResponse>

}