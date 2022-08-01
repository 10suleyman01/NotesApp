package com.suleyman.notesapp.domain.repository

import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.ListTasks

interface TasksRepository {
    suspend fun tasks(): ListTasks

    suspend fun searchTasksByTitle(title: String): ListTasks

    suspend fun getTaskById(id: Long): TaskEntity

    suspend fun sortByCompleted(): ListTasks

    suspend fun insert(task: TaskEntity)

    suspend fun delete(task: TaskEntity)
}