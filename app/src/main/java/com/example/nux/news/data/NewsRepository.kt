package com.example.nux.news.data

import com.example.nux.news.data.local.NewsLocalDataSource
import com.example.nux.news.data.models.News
import com.example.nux.news.data.remote.NewsRemoteDataSource
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton open class NewsRepository @Inject constructor(remoteData: NewsRemoteDataSource, localData:
NewsLocalDataSource)
    : NewsRepositoryDataSource {

    var mRemoteData = remoteData

    var mLocalData = localData

    override fun loadNewses(): Observable<List<News>> {
        return mRemoteData.loadNewses()
                .flatMap { newses ->
                    Observable.fromArray(newses)
                            .doOnNext { nextNewses ->
                                mLocalData.saveNewses(nextNewses)
                            }
                }
    }

}
