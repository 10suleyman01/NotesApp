package com.suleyman.notesapp.data

import com.suleyman.notesapp.db.dao.NotesDao
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.other.ListNotes

class DatabaseNotesRepository(
    private val dao: NotesDao
): NotesRepository {
    override suspend fun notes(): ListNotes {
        return dao.notes()
    }

    override suspend fun insert(note: NoteEntity) {
        dao.insert(note)
    }

    override suspend fun delete(note: NoteEntity) {
        dao.delete(note)
    }
}