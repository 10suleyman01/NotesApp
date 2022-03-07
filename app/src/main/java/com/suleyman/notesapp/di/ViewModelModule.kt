package com.suleyman.notesapp.di

import com.suleyman.notesapp.ui.notes.NoteViewModel
import com.suleyman.notesapp.ui.tasks.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        NoteViewModel(useCases = get())
    }

    viewModel {
        TasksViewModel(useCases = get())
    }

}