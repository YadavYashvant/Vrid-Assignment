package com.yashvant.vrid_assignment.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.yashvant.vrid_assignment.data.model.Excerpt
import com.yashvant.vrid_assignment.data.model.Title

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromTitle(title: Title): String {
        return gson.toJson(title)
    }

    @TypeConverter
    fun toTitle(titleString: String): Title {
        return gson.fromJson(titleString, Title::class.java)
    }

    @TypeConverter
    fun fromExcerpt(excerpt: Excerpt): String {
        return gson.toJson(excerpt)
    }

    @TypeConverter
    fun toExcerpt(excerptString: String): Excerpt {
        return gson.fromJson(excerptString, Excerpt::class.java)
    }
}

