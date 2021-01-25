package com.dara.ulessontask.network

import com.dara.ulessontask.data.ApiResponse
import retrofit2.http.GET

interface ULessonApiService {

    @GET("content/grade")
    suspend fun getContent() : ApiResponse

}
