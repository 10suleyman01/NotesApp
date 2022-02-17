package com.suleyman.notesapp.di

import com.suleyman.notesapp.domain.usecase.CreateAndSaveNoteUseCase
import com.suleyman.notesapp.domain.usecase.GetListNotesUseCase
import org.koin.dsl.module

val useCasesModule = module {

    single<CreateAndSaveNoteUseCase> {
        CreateAndSaveNoteUseCase(repository = get())
    }

    single<GetListNotesUseCase> {
        GetListNotesUseCase(repository = get())
    }

}