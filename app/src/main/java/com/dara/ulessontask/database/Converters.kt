package com.dara.ulessontask.database

import androidx.room.TypeConverter
import com.dara.ulessontask.data.Chapter
import com.dara.ulessontask.data.Lesson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters {

    @TypeConverter
    fun fromChapterList(string: String): List<Chapter> {
        return Gson().fromJson(string, object : TypeToken<List<Chapter>>() {}.type)
    }

    @TypeConverter
    fun toChapterString(list: List<Chapter>): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromLessonList(string: String): List<Lesson> {
        return Gson().fromJson(string, object : TypeToken<List<Lesson>>() {}.type)
    }

    @TypeConverter
    fun toLessonString(list: List<Lesson>): String {
        return Gson().toJson(list)
    }
}