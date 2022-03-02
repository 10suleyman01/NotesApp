package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.data.Storage
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.repository.NotesRepository

class DeleteNoteUseCase(
    private val storage: Storage
) {

    suspend fun execute(note: NoteEntity) {
        storage.local.delete(note)
    }

}