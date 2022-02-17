package com.suleyman.notesapp.di

import androidx.room.Room
import com.suleyman.notesapp.data.DatabaseNotesRepository
import com.suleyman.notesapp.db.DATABASE_NAME
import com.suleyman.notesapp.db.NotesDatabase
import com.suleyman.notesapp.db.dao.NotesDao
import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.domain.usecase.CreateAndSaveNoteUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val databaseModule = module {

    single<NotesDao> {
        Room.databaseBuilder(androidContext(), NotesDatabase::class.java, DATABASE_NAME).build().notesDao()
    }

    single<NotesRepository> {
        DatabaseNotesRepository(dao = get())
    }

}