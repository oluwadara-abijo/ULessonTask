package com.dara.ulessontask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dara.ulessontask.Repository
import com.dara.ulessontask.data.Resource
import com.dara.ulessontask.data.Subject
import com.dara.ulessontask.database.SubjectDatabase
import com.dara.ulessontask.network.ULessonApiClient

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val service = ULessonApiClient.getService()
    private val subjectDao = SubjectDatabase.getDatabase(application).subjectDao()

    private val repository = Repository(service, subjectDao)

    val contentFromServer: LiveData<Resource<Any>>
    val contentFromDatabase: LiveData<List<Subject>>?

    init {
        contentFromServer = repository.getContent
        contentFromDatabase = repository.subjectsInDatabase
    }

}