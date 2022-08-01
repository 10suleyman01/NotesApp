package com.suleyman.notesapp.db.dao

import androidx.room.*
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.ListTasks

@Dao
interface TasksDao {
    @Query("SELECT * FROM tasks")
    suspend fun tasks(): ListTasks

    @Query("SELECT * FROM tasks WHERE title LIKE :title")
    suspend fun searchTasksByTitle(title: String): ListTasks

    @Query("SELECT * FROM tasks WHERE :id == id")
    suspend fun getTaskById(id: Long): TaskEntity

    @Query("SELECT * FROM tasks ORDER BY completed")
    suspend fun sortByCompleted(): ListTasks

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)
}