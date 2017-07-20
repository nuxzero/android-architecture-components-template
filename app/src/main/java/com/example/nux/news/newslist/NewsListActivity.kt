package com.example.nux.news.newslist

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.nux.news.R

class NewsListActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_list)

        if (savedInstanceState == null) {

        }
    }

    override fun getLifecycle(): LifecycleRegistry {
        return lifecycleRegistry
    }

}
