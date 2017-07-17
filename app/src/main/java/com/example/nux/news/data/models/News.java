package com.example.nux.news.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@Entity
public class News {
    @PrimaryKey
    public String title;
    public String author;
    public String description;
    public String url;
    public String urlToImage;
    public Date publishedAt;
}
