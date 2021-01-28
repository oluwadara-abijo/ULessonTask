package com.dara.ulessontask

import androidx.lifecycle.liveData
import com.dara.ulessontask.data.Resource
import com.dara.ulessontask.database.SubjectDao
import com.dara.ulessontask.network.ULessonApiService
import kotlinx.coroutines.Dispatchers

/**
 * This class handles data operations
 */

class Repository(private val service: ULessonApiService, private val subjectDao: SubjectDao) {

    // Get users from database
    val subjectsInDatabase = subjectDao.getSubjects()

    // Get content from server
    val getContent = liveData(Dispatchers.IO) {
        try {
            emit(Resource.Loading<Any>())
            val response = service.getContent()
            subjectDao.addSubjects(response.data.subjects)
            emit(Resource.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Resource.Failure<String>(e.localizedMessage))
        }
    }
}