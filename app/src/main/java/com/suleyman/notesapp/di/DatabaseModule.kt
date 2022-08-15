package com.suleyman.notesapp.di

import androidx.room.Room
import com.suleyman.notesapp.data.DatabaseNotesRepository
import com.suleyman.notesapp.data.StorageNotes
import com.suleyman.notesapp.db.DATABASE_NAME
import com.suleyman.notesapp.db.NotesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidContext(), NotesDatabase::class.java, DATABASE_NAME).build().notesDao()
    }

    single<DatabaseNotesRepository>() {
        DatabaseNotesRepository(dao = get())
    }

    single {
        StorageNotes(
            local = get(),
        )
    }
}