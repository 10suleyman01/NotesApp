package com.suleyman.notesapp.data

import com.suleyman.notesapp.db.dao.TasksDao
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.domain.repository.TasksRepository
import com.suleyman.notesapp.other.ListTasks

class DatabaseTasksRepository(
    private val dao: TasksDao
): TasksRepository {

    override suspend fun tasks() = dao.tasks()

    override suspend fun searchTasksByTitle(title: String) = dao.searchTasksByTitle(title)

    override suspend fun insert(task: TaskEntity) = dao.insert(task)

    override suspend fun getTaskById(id: Long): TaskEntity = dao.getTaskById(id)

    override suspend fun sortByCompleted(): ListTasks = dao.sortByCompleted()

    override suspend fun delete(task: TaskEntity) = dao.delete(task)
}