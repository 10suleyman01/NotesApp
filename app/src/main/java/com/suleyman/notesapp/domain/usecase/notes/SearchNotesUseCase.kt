package com.suleyman.notesapp.domain.usecase.notes

import com.suleyman.notesapp.data.StorageNotes
import com.suleyman.notesapp.other.ListNotes

class SearchNotesUseCase(
    private val storage: StorageNotes
) {

    suspend fun execute(title: String): ListNotes {
        return storage.local.search(title)
    }

}