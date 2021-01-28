package com.dara.ulessontask.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class RecentLesson(
    @PrimaryKey val id: Int,
    val name: String,
    val icon: String,
    val media_url: String,
    val subject_id: Int,
    val chapter_id: Int,
    val subjectName: String,
) : Parcelable