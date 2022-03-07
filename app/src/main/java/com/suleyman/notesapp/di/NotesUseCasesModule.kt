package com.suleyman.notesapp.di

import com.suleyman.notesapp.domain.usecase.notes.*
import org.koin.dsl.module

val notesUseCasesModule = module {

    single {
        CreateAndSaveNoteUseCase(storage = get())
    }

    single {
        GetListNotesUseCase(storage = get())
    }

    single {
        DeleteNoteUseCase(storage = get())
    }

    single {
        SearchNotesUseCase(storage = get())
    }

    single {
        WrapperNotesUseCases(
            createAndSaveNoteUseCase = get(),
            getListNotesUseCase = get(),
            deleteNoteUseCase = get(),
            searchNotesUseCase = get()
        )
    }

}