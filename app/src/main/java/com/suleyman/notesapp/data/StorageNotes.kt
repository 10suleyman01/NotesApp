package com.suleyman.notesapp.data

import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.other.ListNotes

class StorageNotes(
    val local: DatabaseNotesRepository,
): NotesRepository {

    override suspend fun notes(): ListNotes {
        return local.notes()
    }

    override suspend fun insert(noteModel: NoteEntity) {
        local.insert(noteModel)
    }

    override suspend fun search(title: String): ListNotes {
        return local.notes()
    }

    override suspend fun delete(noteModel: NoteEntity) {
        local.delete(noteModel)
    }
}