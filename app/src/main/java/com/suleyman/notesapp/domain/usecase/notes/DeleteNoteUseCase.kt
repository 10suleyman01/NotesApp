package com.suleyman.notesapp.domain.usecase.notes

import com.suleyman.notesapp.data.StorageNotes
import com.suleyman.notesapp.domain.entity.NoteEntity

class DeleteNoteUseCase(
    private val storage: StorageNotes
) {
    suspend fun execute(note: NoteEntity) = storage.local.delete(note)
}