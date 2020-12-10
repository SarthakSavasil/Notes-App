package com.sarthaksavasil.notes_app.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NoteDao {

    @Insert
    fun addNote(notes : Notes)

    @Query("SELECT * FROM notes ORDER BY id DESC")
    fun getAllNotes(): List<Notes>

    @Insert
    fun addMultipleNotes(vararg notes : Notes)

}