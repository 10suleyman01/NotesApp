package com.suleyman.notesapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.suleyman.notesapp.db.dao.NotesDao
import com.suleyman.notesapp.domain.entity.NoteEntity

const val DATABASE_NAME = "notes_database"

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}