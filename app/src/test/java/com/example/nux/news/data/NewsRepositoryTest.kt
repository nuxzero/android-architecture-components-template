package com.example.nux.news.data

import com.example.nux.news.data.local.NewsLocalDataSource
import com.example.nux.news.data.models.News
import com.example.nux.news.data.models.NewsesResponse
import com.example.nux.news.data.remote.NewsRemoteDataSource
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import utils.ReadJsonUtils

import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

/**
 * Created by Natthawut Hemathulin on 6/2/2017 AD.
 * Email: natthawut1991@gmail.com
 */

@RunWith(MockitoJUnitRunner::class)
class NewsRepositoryTest {

    private val NEWSES_RESPONSE = ReadJsonUtils()
            .getJsonToMock("get_news_list.json", NewsesResponse::class.java)

    lateinit var mRepository: NewsRepository

    @Mock
    lateinit var mRemoteDataSource: NewsRemoteDataSource

    @Mock
    lateinit var mLocalDataSource: NewsLocalDataSource

    @Before
    fun setUp() {

        mRepository = NewsRepository(mRemoteDataSource, mLocalDataSource)

    }

    @Test
    fun loadNewses_whenLoadNewsesSuccessThenReturnNewsesResponse() {

        // Give
        val NEWSES = NEWSES_RESPONSE.newses
        `when`(mRemoteDataSource.loadNewsList()).thenReturn(Observable.just(NEWSES))

        // When
        val testObserver = TestObserver<List<News>>()
        mRepository.loadNewsList().subscribe(testObserver)

        // Then
        verify(mRemoteDataSource).loadNewsList()
        verify(mLocalDataSource).saveNewsList(NEWSES)
        testObserver.assertValue(NEWSES)
    }

}
