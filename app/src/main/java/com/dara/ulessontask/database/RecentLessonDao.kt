package com.dara.ulessontask.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dara.ulessontask.data.RecentLesson

/**
 * Provides methods to interact with data in the database
 */

@Dao
interface RecentLessonDao {

    // Get recently watched lessons
    @Query("SELECT * FROM recentlesson")
    fun getRecentLessons(): LiveData<List<RecentLesson>>?

    // Add lesson to recently watched
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecentLesson(lesson: RecentLesson)
}