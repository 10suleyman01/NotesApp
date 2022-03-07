package com.suleyman.notesapp.di

import com.suleyman.notesapp.domain.usecase.tasks.CreateAndSaveTaskUseCase
import com.suleyman.notesapp.domain.usecase.tasks.DeleteTaskUseCase
import com.suleyman.notesapp.domain.usecase.tasks.GetListTasksUseCase
import com.suleyman.notesapp.domain.usecase.tasks.WrapperTasksUseCases
import org.koin.dsl.module

val taskUseCasesModule = module {

    single {
        GetListTasksUseCase(storage = get())
    }

    single {
        CreateAndSaveTaskUseCase(storage = get())
    }

    single {
        DeleteTaskUseCase(storage = get())
    }

    single {
        WrapperTasksUseCases(
            getListTasksUseCase = get(),
            createAndSaveTaskUseCase = get(),
            deleteTaskUseCase = get()
        )
    }

}