package com.suleyman.notesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suleyman.notesapp.db.dao.NotesDao
import com.suleyman.notesapp.db.dao.TasksDao
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity

const val DATABASE_TASKS_NAME = "tasks_database"

@Database(entities = [TaskEntity::class], version = 1, exportSchema = true)
abstract class TasksDatabase : RoomDatabase() {
    abstract fun tasksDao(): TasksDao
}