package com.suleyman.notesapp.domain.usecase.notes

import com.suleyman.notesapp.data.StorageNotes
import com.suleyman.notesapp.domain.entity.NoteEntity

class CreateAndSaveNoteUseCase(
    private val storage: StorageNotes
) {
    suspend fun execute(note: NoteEntity) = storage.local.insert(note)
}