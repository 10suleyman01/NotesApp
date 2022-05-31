package com.suleyman.notesapp.data

import com.suleyman.notesapp.db.dao.NotesDao
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.other.ListNotes

class DatabaseNotesRepository(
    private val dao: NotesDao
): NotesRepository {

    override suspend fun notes(): ListNotes = dao.notes()

    override suspend fun insert(noteModel: NoteEntity) = dao.insert(noteModel)

    override suspend fun search(title: String): ListNotes = dao.searchNotesByTitle(title)

    override suspend fun sortByBookmarked(): ListNotes = dao.sortByBookmarked()

    override suspend fun delete(noteModel: NoteEntity) = dao.delete(noteModel)

}