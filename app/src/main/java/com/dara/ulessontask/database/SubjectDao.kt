package com.dara.ulessontask.database

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dara.ulessontask.data.Chapter
import com.dara.ulessontask.data.Lesson
import com.dara.ulessontask.data.Subject

/**
 * Provides methods to interact with data in the database
 */

interface SubjectDao {

    // Get list of all subjects
    @Query("SELECT * FROM subject")
    fun getSubjects(): LiveData<List<Subject>>?

    // Add a subject from the API to the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addSubjects(subjects: List<Subject>)

    // Get list of chapters under a subject
    @Query("SELECT * FROM chapter WHERE id=:subjectId")
    fun getChapters(subjectId: Int): LiveData<List<Chapter>>?

    // Add a chapter from the API to the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addChapter(chapter: Chapter)

    // Get list of lessons under a chapter
    @Query("SELECT * FROM lesson WHERE id=:chapterId")
    fun getLessons(chapterId: Int): LiveData<List<Lesson>>?

    // Add a lesson from the API to the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addLesson(lesson: Lesson)
}