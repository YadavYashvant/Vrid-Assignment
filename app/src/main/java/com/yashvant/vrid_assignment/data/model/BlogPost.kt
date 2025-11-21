package com.yashvant.vrid_assignment.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "blog_posts")
data class BlogPost(
    @PrimaryKey
    @SerializedName("id")
    val id: Int,

    @SerializedName("date")
    val date: String,

    @SerializedName("title")
    val title: Title,

    @SerializedName("excerpt")
    val excerpt: Excerpt,

    @SerializedName("link")
    val link: String,

    @SerializedName("featured_media")
    val featuredMedia: Int,

    @SerializedName("jetpack_featured_media_url")
    val featuredMediaUrl: String?
)

data class Title(
    @SerializedName("rendered")
    val rendered: String
)

data class Excerpt(
    @SerializedName("rendered")
    val rendered: String
)

