package com.dara.ulessontask.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * This models the server response
 */
data class ApiResponse(val data: ResponseData)

data class ResponseData(val status: String, val message: String, val subjects: List<Subject>)

@Parcelize
data class Subject(val id: Int, val name: String, val icon: String, val chapters: List<Chapter>) :
    Parcelable

@Parcelize
data class Chapter(val id: Int, val name: String, val lessons: List<Lesson>) : Parcelable

@Parcelize
data class Lesson(
    val id: Int,
    val name: String,
    val icon: String,
    val media_url: String,
    val subject_id: Int,
    val chapter_id: Int
) : Parcelable
