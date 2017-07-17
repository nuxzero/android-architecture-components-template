package com.example.nux.news.local

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.nux.news.data.local.LocalDatabase
import com.example.nux.news.data.local.NewsLocalData
import com.example.nux.news.data.models.NewsesResponse
import junit.framework.Assert
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import utils.ReadJsonUtils

@RunWith(AndroidJUnit4::class)
class NewsLocalDataTest {

    private val newsesResponse = ReadJsonUtils().getJsonToMock("get_news_list.json",
            NewsesResponse::class.java)

    private lateinit var localData: NewsLocalData

    private lateinit var database: LocalDatabase

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()

        database = Room.inMemoryDatabaseBuilder(context, LocalDatabase::class.java).build()

        localData = NewsLocalData(database)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun loadNewses_whenLoadNewsesThenReturnNewses() {
        // Give
        for (news in newsesResponse.newses) {
            database.newsDao().insertNews(news)
        }

        // When
        val actualNewses = localData.loadNewses()

        // Then
        Assert.assertEquals(newsesResponse.newses.size, actualNewses.size)
    }

    @Test
    fun saveNewses_whenSaveNewsesThenSaveNewsesToLocalDatabase() {
        // Give

        // When
        for (news in newsesResponse.newses) {
            database.newsDao().insertNews(news)
        }

        // Then
        Assert.assertEquals(newsesResponse.newses.size, database.newsDao().news.size)
    }

}