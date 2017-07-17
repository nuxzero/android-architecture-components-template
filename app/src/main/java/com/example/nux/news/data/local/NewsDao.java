package com.example.nux.news.data.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.nux.news.data.models.News;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news")
    List<News> getNews();

    @Insert
    void insertNews(News news);

    @Update
    void updateNews(News news);

}
