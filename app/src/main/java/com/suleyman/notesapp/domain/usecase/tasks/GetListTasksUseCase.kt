package com.suleyman.notesapp.domain.usecase.tasks

import com.suleyman.notesapp.data.StorageTasks
import com.suleyman.notesapp.other.ListTasks

class GetListTasksUseCase(
    private val storage: StorageTasks
) {
    suspend fun execute(isSorted: Boolean = false): ListTasks {
        return if (isSorted) storage.local.sortByCompleted() else
            storage.local.tasks()
    }
}