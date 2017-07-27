package com.example.nux.news.news

import com.example.nux.news.data.NewsRepositoryComponent
import com.example.nux.news.util.FragmentScoped
import dagger.Component

@FragmentScoped
@Component(dependencies = arrayOf(NewsRepositoryComponent::class))
interface NewsViewModelComponent {

    fun inject(fragment: NewsFragment)

}