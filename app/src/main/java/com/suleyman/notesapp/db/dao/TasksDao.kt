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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskEntity)

    @Delete
    suspend fun delete(task: TaskEntity)
}