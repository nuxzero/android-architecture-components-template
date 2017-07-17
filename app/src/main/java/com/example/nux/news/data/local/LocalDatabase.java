package com.example.nux.news.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.example.nux.news.data.models.News;

@Database(entities = {News.class}, version = 1)
@TypeConverters({LocalTypeConverters.class})
public abstract class LocalDatabase extends RoomDatabase {

    public abstract NewsDao newsDao();

}
