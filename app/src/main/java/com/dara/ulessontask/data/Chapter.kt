package com.dara.ulessontask.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Chapter(
    val id: Int,
    val name: String,
    val lessons: List<Lesson>
) : Parcelable