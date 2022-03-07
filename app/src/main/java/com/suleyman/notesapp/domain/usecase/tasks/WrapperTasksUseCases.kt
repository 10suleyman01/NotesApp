package com.suleyman.notesapp.domain.usecase.tasks

data class WrapperTasksUseCases(
    val createAndSaveTaskUseCase: CreateAndSaveTaskUseCase,
    val getListTasksUseCase: GetListTasksUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase
)