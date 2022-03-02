package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.data.Storage
import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.domain.entity.NoteEntity

class CreateAndSaveNoteUseCase(
    private val storage: Storage
) {

    suspend fun execute(note: NoteEntity) {
        storage.local.insert(note)
    }

}