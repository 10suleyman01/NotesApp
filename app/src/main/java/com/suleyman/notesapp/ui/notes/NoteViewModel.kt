package com.suleyman.notesapp.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.usecase.CreateAndSaveNoteUseCase
import com.suleyman.notesapp.domain.usecase.GetListNotesUseCase
import com.suleyman.notesapp.other.ListNotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class NoteViewModel(
    private val createAndSaveNoteUseCase: CreateAndSaveNoteUseCase,
    private val getListNotesUseCase: GetListNotesUseCase
): ViewModel() {

    private val _states: MutableStateFlow<NotesEvent> = MutableStateFlow(NotesEvent.None)
    var states = _states.asStateFlow()

    fun save(note: NoteEntity) = viewModelScope.launch {
        createAndSaveNoteUseCase.execute(note)
        _states.value = NotesEvent.NewNote(note)
    }

    fun notes() = viewModelScope.launch {
        _states.value = NotesEvent.Loading(true)
        val notes = getListNotesUseCase.execute()
        _states.value = NotesEvent.GetNotes(notes)
        _states.value = NotesEvent.Loading(false)
    }

    sealed class NotesEvent {
        object None: NotesEvent()
        data class Loading(val isLoading: Boolean): NotesEvent()
        data class GetNotes(val notes: ListNotes): NotesEvent()
        data class NewNote(val note: NoteEntity): NotesEvent()
    }

}