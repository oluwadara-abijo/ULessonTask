package com.dara.ulessontask.data

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Chapter(
    val id: Int,
    val name: String,
    val lessons: List<Lesson>
) : Parcelable