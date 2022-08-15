package com.suleyman.notesapp.ui.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.usecase.notes.WrapperNotesUseCases
import com.suleyman.notesapp.other.ListNotes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class NoteViewModel(
    private val useCases: WrapperNotesUseCases
) : ViewModel() {

    private val _states: MutableStateFlow<NotesEvent> = MutableStateFlow(NotesEvent.None)
    var states = _states.asStateFlow()

    fun save(note: NoteEntity) = viewModelScope.launch {
        useCases.createAndSaveNoteUseCase.execute(note)
        _states.value = NotesEvent.NewNote(note)
    }

    fun delete(note: NoteEntity) = launchWithLoading {
        useCases.deleteNoteUseCase.execute(note)
        _states.value = NotesEvent.Deleted
    }

    fun search(title: String) = launchWithLoading {
        val searchedNotes = useCases.searchNotesUseCase.execute(title)
        _states.value = NotesEvent.GetNotes(searchedNotes)
    }

    fun notes() = launchWithLoading {
        loadNotes()
    }

    private fun loadNotes(isSorted: Boolean = false) = viewModelScope.launch {
        val notes = useCases.getListNotesUseCase.execute(isSorted = true)
        _states.value = NotesEvent.GetNotes(notes)
    }

    private fun launchWithLoading(body: suspend () -> Unit) =
        viewModelScope.launch {
            _states.value = NotesEvent.Loading(true)
            body()
            _states.value = NotesEvent.Loading(false)
        }

    sealed class NotesEvent {
        object None : NotesEvent()
        object Deleted : NotesEvent()
        data class Loading(val isLoading: Boolean) : NotesEvent()
        data class GetNotes(val notes: ListNotes) : NotesEvent()
        data class NewNote(val note: NoteEntity) : NotesEvent()
    }

}