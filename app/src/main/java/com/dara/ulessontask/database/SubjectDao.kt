package com.dara.ulessontask.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dara.ulessontask.data.RecentLesson
import com.dara.ulessontask.data.Subject

/**
 * Provides methods to interact with data in the database
 */

@Dao
interface SubjectDao {

    // Get list of all subjects
    @Query("SELECT * FROM subject")
    fun getSubjects(): LiveData<List<Subject>>?

    // Add a subject from the API to the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSubjects(subjects: List<Subject>)

    // Get subjects by id
    @Query("SELECT * FROM subject WHERE id=:subjectId")
    fun getSubjectById(subjectId: Int): LiveData<Subject>

}