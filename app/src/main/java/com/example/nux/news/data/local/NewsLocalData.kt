package com.example.nux.news.data.local

import android.database.sqlite.SQLiteConstraintException
import com.example.nux.news.data.models.News
import io.reactivex.Flowable

class NewsLocalData(val localDatabase: LocalDatabase) : NewsLocalDataSource {

    override fun loadNewsList(): List<News> {
        return localDatabase.newsDao().newsList
    }

    override fun loadNews(title: String): Flowable<News> {
        return localDatabase.newsDao().getNews(title)
    }

    override fun saveNewsList(newsList: List<News>) {
        for (news in newsList) {
            try {
                localDatabase.newsDao().insertNews(news)
            } catch (e: SQLiteConstraintException) {
                localDatabase.newsDao().updateNews(news)
            }
        }
    }

}
