package com.suleyman.notesapp.domain.usecase.tasks

import com.suleyman.notesapp.data.StorageTasks
import com.suleyman.notesapp.domain.entity.TaskEntity

class GetTaskByIdUseCase(
    private val storage: StorageTasks
) {

    suspend fun execute(id: Long): TaskEntity {
        return storage.getTaskById(id)
    }

}