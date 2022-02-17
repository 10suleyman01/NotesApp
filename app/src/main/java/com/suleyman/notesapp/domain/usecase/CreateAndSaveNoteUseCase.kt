package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.domain.entity.NoteEntity

class CreateAndSaveNoteUseCase(
    private val notesRepository: NotesRepository
) {

    suspend fun execute(note: NoteEntity) {

        notesRepository.insert(
            noteModel = note
        )
    }

}