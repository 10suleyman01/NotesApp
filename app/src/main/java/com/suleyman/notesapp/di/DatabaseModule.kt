package com.suleyman.notesapp.di

import androidx.room.Room
import com.suleyman.notesapp.data.DatabaseNotesRepository
import com.suleyman.notesapp.data.DatabaseTasksRepository
import com.suleyman.notesapp.data.StorageNotes
import com.suleyman.notesapp.data.StorageTasks
import com.suleyman.notesapp.db.DATABASE_NAME
import com.suleyman.notesapp.db.DATABASE_TASKS_NAME
import com.suleyman.notesapp.db.NotesDatabase
import com.suleyman.notesapp.db.TasksDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidContext(), NotesDatabase::class.java, DATABASE_NAME).build().notesDao()
    }

    single {
        Room.databaseBuilder(androidContext(), TasksDatabase::class.java, DATABASE_TASKS_NAME).build().tasksDao()
    }

    single<DatabaseNotesRepository>() {
        DatabaseNotesRepository(dao = get())
    }

    single<DatabaseTasksRepository>() {
        DatabaseTasksRepository(dao = get())
    }


    single {
        StorageNotes(
            local = get(),
        )
    }

    single {
        StorageTasks(
            local = get()
        )
    }

}