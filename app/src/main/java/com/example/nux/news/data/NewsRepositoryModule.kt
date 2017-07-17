package com.example.nux.news.data

import android.arch.persistence.room.Room
import android.content.Context
import com.example.nux.news.data.local.LocalDatabase
import com.example.nux.news.data.local.NewsLocalData
import com.example.nux.news.data.local.NewsLocalDataSource
import com.example.nux.news.data.remote.NewsRemoteData
import com.example.nux.news.data.remote.NewsRemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NewsRepositoryModule(val context: Context) {

    companion object {
        val BASE_URL = "https://newsapi.org/v1/"
    }

    @Singleton
    @Provides
    fun provideNewsRemoteData(): NewsRemoteDataSource {
        return NewsRemoteData(BASE_URL)
    }

    @Singleton
    @Provides
    fun provideNewsLocalData(): NewsLocalDataSource {
        val database: LocalDatabase = Room.databaseBuilder(context, LocalDatabase::class.java,
                "news-database.db").build()

        return NewsLocalData(database)
    }

}
