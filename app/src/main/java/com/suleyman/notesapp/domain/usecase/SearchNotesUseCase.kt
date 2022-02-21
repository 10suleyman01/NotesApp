package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.other.ListNotes

class SearchNotesUseCase(
    private val notesRepository: NotesRepository
) {

    suspend fun execute(title: String): ListNotes {
        return notesRepository.search(title)
    }

}