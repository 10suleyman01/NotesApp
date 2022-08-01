package com.suleyman.notesapp.data

import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.domain.repository.TasksRepository
import com.suleyman.notesapp.other.ListTasks

class StorageTasks(
    val local: DatabaseTasksRepository
): TasksRepository {
    
    override suspend fun tasks() = local.tasks()

    override suspend fun searchTasksByTitle(title: String) = local.searchTasksByTitle(title)

    override suspend fun getTaskById(id: Long): TaskEntity = local.getTaskById(id)

    override suspend fun sortByCompleted(): ListTasks = local.sortByCompleted()

    override suspend fun insert(task: TaskEntity) = local.insert(task)

    override suspend fun delete(task: TaskEntity) = local.delete(task)
}