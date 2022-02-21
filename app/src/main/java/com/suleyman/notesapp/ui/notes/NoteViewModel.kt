package com.suleyman.notesapp.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.usecase.*
import com.suleyman.notesapp.other.ListNotes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class NoteViewModel(
    private val useCases: WrapperUseCases
) : ViewModel() {

    private val _states: MutableStateFlow<NotesEvent> = MutableStateFlow(NotesEvent.None)
    var states = _states.asStateFlow()

    fun save(note: NoteEntity) = viewModelScope.launch {
        useCases.createAndSaveNoteUseCase.execute(note)
        _states.value = NotesEvent.NewNote(note)
    }

    fun delete(note: NoteEntity) = loadingEvent {
        useCases.deleteNoteUseCase.execute(note)
    }

    fun search(title: String) = loadingEvent {
        val searchedNotes = useCases.searchNotesUseCase.execute(title)
        _states.value = NotesEvent.GetNotes(searchedNotes)
    }

    fun notes() = loadingEvent {
        loadNotes()
    }

    private fun loadNotes() = viewModelScope.launch {
        val notes = useCases.getListNotesUseCase.execute()
        _states.value = NotesEvent.GetNotes(notes)
    }

    private fun loadingEvent(delayInMillis: Long = 0, body: suspend () -> Unit) =
        viewModelScope.launch {
            _states.value = NotesEvent.Loading(true)
            if (delayInMillis > 0) {
                delay(delayInMillis)
            }
            body()
            _states.value = NotesEvent.Loading(false)
        }

    sealed class NotesEvent {
        object None : NotesEvent()
        data class Loading(val isLoading: Boolean) : NotesEvent()
        data class GetNotes(val notes: ListNotes) : NotesEvent()
        data class NewNote(val note: NoteEntity) : NotesEvent()
    }

}