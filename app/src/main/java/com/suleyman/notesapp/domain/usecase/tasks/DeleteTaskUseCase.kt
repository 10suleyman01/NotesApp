package com.suleyman.notesapp.domain.usecase.tasks

import com.suleyman.notesapp.data.StorageNotes
import com.suleyman.notesapp.data.StorageTasks
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity

class DeleteTaskUseCase(
    private val storage: StorageTasks
) {

    suspend fun execute(task: TaskEntity) {
        storage.local.delete(task)
    }

}