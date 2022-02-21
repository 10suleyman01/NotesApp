package com.suleyman.notesapp.domain.repository

import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.ListNotes

interface NotesRepository {
    suspend fun notes(): ListNotes

    suspend fun insert(noteModel: NoteEntity)

    suspend fun search(title: String): ListNotes

    suspend fun delete(noteModel: NoteEntity)
}