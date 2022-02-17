package com.suleyman.notesapp.di

import androidx.room.Room
import com.suleyman.notesapp.data.DatabaseNotesRepository
import com.suleyman.notesapp.db.DATABASE_NAME
import com.suleyman.notesapp.db.NotesDatabase
import com.suleyman.notesapp.db.dao.NotesDao
import com.suleyman.notesapp.domain.repository.NotesRepository
import com.suleyman.notesapp.ui.notes.NoteViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel<NoteViewModel>() {
        NoteViewModel(
            createAndSaveNoteUseCase = get(),
            getListNotesUseCase = get()
        )
    }

}