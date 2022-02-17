package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.other.ListNotes

class GetListNotesUseCase(
    private val notesRepository: NotesRepository
) {

    suspend fun execute(): ListNotes {
        return notesRepository.notes()
    }

}