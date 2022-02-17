package com.suleyman.notesapp.db.dao

import androidx.room.*
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.ListNotes

@Dao
interface NotesDao {
    @Query("SELECT * FROM notes")
    suspend fun notes(): ListNotes

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(noteEntity: NoteEntity)

    @Delete
    suspend fun delete(noteEntity: NoteEntity)
}