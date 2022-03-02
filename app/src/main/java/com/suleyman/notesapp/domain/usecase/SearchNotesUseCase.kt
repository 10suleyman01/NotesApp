package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.data.Storage
import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.other.ListNotes

class SearchNotesUseCase(
    private val storage: Storage
) {

    suspend fun execute(title: String): ListNotes {
        return storage.local.search(title)
    }

}