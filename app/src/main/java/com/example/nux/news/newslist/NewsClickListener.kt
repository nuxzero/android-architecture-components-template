package com.example.nux.news.newslist

import com.example.nux.news.data.models.News

interface NewsClickListener {

    fun onClick(news: News)

}
