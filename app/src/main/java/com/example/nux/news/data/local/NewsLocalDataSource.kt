package com.example.nux.news.data.local

import com.example.nux.news.data.models.News

interface NewsLocalDataSource {

    fun loadNewses() : List<News>

    fun saveNewses(newses: List<News>)

}
