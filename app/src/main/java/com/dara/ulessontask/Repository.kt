package com.dara.ulessontask

import androidx.lifecycle.liveData
import com.dara.ulessontask.data.Resource
import com.dara.ulessontask.network.ULessonApiService
import kotlinx.coroutines.Dispatchers

/**
 * This class handles data operations
 */

class Repository(private val service: ULessonApiService) {

    // Get content from server
    val getContent = liveData(Dispatchers.IO) {
        try {
            emit(Resource.Loading<Any>())
            val response = service.getContent()
            emit(Resource.Success(response))
        } catch (e: java.lang.Exception) {
            emit(Resource.Failure<String>(e.localizedMessage))
        }
    }
}