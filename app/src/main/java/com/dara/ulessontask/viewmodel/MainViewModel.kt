package com.dara.ulessontask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dara.ulessontask.Repository
import com.dara.ulessontask.data.RecentLesson
import com.dara.ulessontask.data.Resource
import com.dara.ulessontask.data.Subject
import com.dara.ulessontask.database.SubjectDatabase
import com.dara.ulessontask.network.ULessonApiClient

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val service = ULessonApiClient.getService()
    private val database = SubjectDatabase.getDatabase(application)
    private val subjectDao = database.subjectDao()
    private val recentLessonDao = database.recentLessonDao()

    private val repository = Repository(service, subjectDao,recentLessonDao)

    val contentFromServer: LiveData<Resource<Any>>
    val contentFromDatabase: LiveData<List<Subject>>?
    val recentLessons: LiveData<List<RecentLesson>>?


    init {
        contentFromServer = repository.getContent
        contentFromDatabase = repository.subjectsInDatabase
        recentLessons = repository.recentLessons
    }

    fun getSubjectById(id: Int) = repository.getSubjectById(id)
    fun addRecentLesson(lesson: RecentLesson) = repository.addRecentLesson(lesson)

}