package com.suleyman.notesapp.di

import com.suleyman.notesapp.domain.usecase.tasks.*
import org.koin.dsl.module

val taskUseCasesModule = module {
    single {
        GetListTasksUseCase(storage = get())
    }

    single {
        CreateAndSaveTaskUseCase(storage = get())
    }

    single {
        GetTaskByIdUseCase(storage = get())
    }

    single {
        DeleteTaskUseCase(storage = get())
    }

    single {
        WrapperTasksUseCases(
            getListTasksUseCase = get(),
            getTaskByIdUseCase = get(),
            createAndSaveTaskUseCase = get(),
            deleteTaskUseCase = get(),
        )
    }
}