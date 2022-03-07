package com.suleyman.notesapp.domain.usecase.notes

data class WrapperNotesUseCases(
    val createAndSaveNoteUseCase: CreateAndSaveNoteUseCase,
    val getListNotesUseCase: GetListNotesUseCase,
    val deleteNoteUseCase: DeleteNoteUseCase,
    val searchNotesUseCase: SearchNotesUseCase
)