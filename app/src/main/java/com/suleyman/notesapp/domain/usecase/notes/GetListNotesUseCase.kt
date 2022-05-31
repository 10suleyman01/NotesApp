package com.suleyman.notesapp.domain.usecase.notes

import com.suleyman.notesapp.data.StorageNotes
import com.suleyman.notesapp.other.ListNotes

class GetListNotesUseCase(
    private val storage: StorageNotes
) {
    suspend fun execute(isSorted: Boolean = false): ListNotes {
        return if (isSorted) storage.local.sortByBookmarked() else
            storage.local.notes()
    }
}