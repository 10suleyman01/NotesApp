package com.suleyman.notesapp.domain.usecase.notes

data class WrapperUseCases(
    val createAndSaveNoteUseCase: CreateAndSaveNoteUseCase,
    val getListNotesUseCase: GetListNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val searchNotesUseCase: SearchNotesUseCase
)