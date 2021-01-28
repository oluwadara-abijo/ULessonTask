package com.dara.ulessontask.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Lesson(
    val id: Int,
    val name: String,
    val icon: String,
    val media_url: String,
    val subject_id: Int,
    val chapter_id: Int
) : Parcelable