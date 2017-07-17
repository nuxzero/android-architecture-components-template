package com.example.nux.news.injection

import com.example.nux.news.util.BaseSchedulerProvider
import dagger.Module
import dagger.Provides
import com.example.nux.news.util.SchedulerProvider

@Module
class AppModule {

    @Provides
    fun provideScheduleProvider(): BaseSchedulerProvider {
        return SchedulerProvider()
    }

}