package com.dara.ulessontask.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class Subject(
    @PrimaryKey val id: Int,
    val name: String,
    val icon: String,
    val chapters: List<Chapter>
) : Parcelable