package com.sarthaksavasil.notes_app.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.locks.Lock

@Database(
    entities = [Notes::class],
    version = 1
)
abstract class NotesDB : RoomDatabase() {

    abstract fun getNoteDao():NoteDao

    companion object{
        @Volatile private var instance : NotesDB? = null
        private val LOCK = Any()

        operator fun invoke (context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }
          private fun buildDatabase(context: Context)= Room.databaseBuilder(
            context.applicationContext,
            NotesDB::class.java,
            "notedatabase"
        ).build()

    }
}