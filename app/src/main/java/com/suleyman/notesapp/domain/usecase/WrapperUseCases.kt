package com.suleyman.notesapp.domain.usecase

data class WrapperUseCases(
    val createAndSaveNoteUseCase: CreateAndSaveNoteUseCase,
    val getListNotesUseCase: GetListNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val searchNotesUseCase: SearchNotesUseCase
)