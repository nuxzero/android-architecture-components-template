package com.example.nux.news.news

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.nux.news.R

class NewsActivity : AppCompatActivity(), LifecycleRegistryOwner {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        if (intent != null) {
            val title = intent.extras.getString(NewsArgument.ARG_TITLE)

            supportFragmentManager.beginTransaction()
                    .replace(R.id.news_content, NewsFragment.newInstance(title))
                    .commit()
        }
    }

    override fun getLifecycle(): LifecycleRegistry {
        return LifecycleRegistry(this)
    }

}
