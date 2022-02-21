package com.suleyman.notesapp.di

import com.suleyman.notesapp.domain.usecase.*
import org.koin.dsl.module

val useCasesModule = module {

    single {
        CreateAndSaveNoteUseCase(notesRepository = get())
    }

    single {
        GetListNotesUseCase(notesRepository = get())
    }

    single {
        DeleteNoteUseCase(notesRepository = get())
    }

    single {
        SearchNotesUseCase(notesRepository = get())
    }

    single {
        WrapperUseCases(
            createAndSaveNoteUseCase = get(),
            getListNotesUseCase = get(),
            deleteNoteUseCase = get(),
            searchNotesUseCase = get()
        )
    }

}