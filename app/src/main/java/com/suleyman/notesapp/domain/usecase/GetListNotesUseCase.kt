package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.data.Storage
import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.other.ListNotes

class GetListNotesUseCase(
    private val storage: Storage
) {

    suspend fun execute(isLocal: Boolean): ListNotes {
        return storage.local.notes()
    }

}