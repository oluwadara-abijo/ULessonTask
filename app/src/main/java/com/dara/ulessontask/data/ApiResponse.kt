package com.dara.ulessontask.data

/**
 * This models the server response
 */
data class ApiResponse(val data: ResponseData)

data class ResponseData(val status: String, val message: String, val subjects: List<Subject>)

