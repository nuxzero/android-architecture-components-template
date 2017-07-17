package com.example.nux.news.data.models

import com.google.gson.annotations.SerializedName

data class NewsesResponse(var status: String,
                          var source: String,
                          var sortBy: String,
                          @SerializedName("articles") var newses: List<News>) {

}
