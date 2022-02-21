package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.repository.NotesRepository

class DeleteNoteUseCase(
    private val notesRepository: NotesRepository
) {

    suspend fun execute(note: NoteEntity) {

        notesRepository.delete(note)

    }

}