package com.suleyman.notesapp.domain.usecase.tasks

data class WrapperTasksUseCases(
    val createAndSaveTaskUseCase: CreateAndSaveTaskUseCase,
    val getTaskByIdUseCase: GetTaskByIdUseCase,
    val getListTasksUseCase: GetListTasksUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase
)