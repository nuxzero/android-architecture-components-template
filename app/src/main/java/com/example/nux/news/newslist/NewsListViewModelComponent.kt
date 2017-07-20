package com.example.nux.news.newslist

import com.example.nux.news.data.NewsRepositoryComponent
import com.example.nux.news.util.FragmentScoped
import dagger.Component

@FragmentScoped
@Component(dependencies = arrayOf(NewsRepositoryComponent::class))
interface NewsListViewModelComponent {

    fun inject(fragment: NewsListFragment)

}