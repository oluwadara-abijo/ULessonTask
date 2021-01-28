package com.dara.ulessontask.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dara.ulessontask.data.Chapter
import com.dara.ulessontask.data.Lesson
import com.dara.ulessontask.data.Subject

@Database(
    entities = [Subject::class], version = 1, exportSchema = false
)
@TypeConverters(Converters::class)
abstract class SubjectDatabase : RoomDatabase() {

    abstract fun subjectDao(): SubjectDao

    companion object {
        // Singleton prevents multiple instances of database opening at the same time.
        @Volatile
        private var INSTANCE: SubjectDatabase? = null

        fun getDatabase(context: Context): SubjectDatabase {
            // Create the database if INSTANCE is not null
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext, SubjectDatabase::class.java, "subject_database"
                ).build()
                INSTANCE = instance
                // Return instance
                instance
            }
        }
    }
}