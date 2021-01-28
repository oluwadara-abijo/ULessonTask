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

fun RecentLesson.toLesson() = Lesson(
    id = id,
    name = name,
    icon = icon,
    media_url = media_url,
    subject_id = subject_id,
    chapter_id = chapter_id
)