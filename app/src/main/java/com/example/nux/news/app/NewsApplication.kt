package com.example.nux.news.app

import android.app.Application
import com.example.nux.news.data.DaggerNewsRepositoryComponent
import com.example.nux.news.data.NewsRepositoryComponent
import com.example.nux.news.data.NewsRepositoryModule

class NewsApplication: Application() {

    lateinit var newsRepositoryComponent: NewsRepositoryComponent

    override fun onCreate() {
        super.onCreate()

        newsRepositoryComponent = DaggerNewsRepositoryComponent.builder()
                .newsRepositoryModule(NewsRepositoryModule(this.applicationContext))
                .appModule(AppModule())
                .build()
    }

}