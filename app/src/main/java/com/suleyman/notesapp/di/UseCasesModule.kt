package com.suleyman.notesapp.di

import com.suleyman.notesapp.domain.usecase.CreateAndSaveNoteUseCase
import com.suleyman.notesapp.domain.usecase.GetListNotesUseCase
import org.koin.dsl.module

val useCasesModule = module {

    single {
        CreateAndSaveNoteUseCase(notesRepository = get())
    }

    single {
        GetListNotesUseCase(notesRepository = get())
    }

}