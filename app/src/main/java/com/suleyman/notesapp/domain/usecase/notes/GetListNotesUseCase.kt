package com.suleyman.notesapp.domain.usecase.notes

import com.suleyman.notesapp.data.StorageNotes
import com.suleyman.notesapp.other.ListNotes

class GetListNotesUseCase(
    private val storage: StorageNotes
) {
    suspend fun execute(isLocal: Boolean): ListNotes = storage.local.notes()
}