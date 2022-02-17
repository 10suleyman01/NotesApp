package com.suleyman.notesapp.di

import com.suleyman.notesapp.ui.notes.NoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        NoteViewModel(
            createAndSaveNoteUseCase = get(),
            getListNotesUseCase = get()
        )
    }

}