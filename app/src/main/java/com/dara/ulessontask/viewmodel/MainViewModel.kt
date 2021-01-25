package com.dara.ulessontask.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dara.ulessontask.Repository
import com.dara.ulessontask.data.Resource
import com.dara.ulessontask.network.ULessonApiClient

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val service = ULessonApiClient.getService()
    private val repository = Repository(service)
    val content: LiveData<Resource<Any>>

    init {
        content = repository.getContent
    }

}