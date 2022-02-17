package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.domain.entity.NoteEntity

class CreateAndSaveNoteUseCase(
    private val repository: NotesRepository
) {

    suspend fun execute(note: NoteEntity) {

        repository.insert(
            noteModel = note
        )
    }

}