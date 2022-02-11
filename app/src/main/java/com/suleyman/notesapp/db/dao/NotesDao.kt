package com.suleyman.notesapp.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.suleyman.notesapp.model.NoteModel
import com.suleyman.notesapp.other.ListNotes

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    suspend fun notes(): ListNotes

    @Insert
    suspend fun insert(noteModel: NoteModel)

    @Delete
    suspend fun delete(noteModel: NoteModel)
}