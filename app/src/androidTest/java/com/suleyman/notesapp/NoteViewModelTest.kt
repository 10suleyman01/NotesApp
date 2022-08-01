package com.suleyman.notesapp

import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.usecase.notes.WrapperNotesUseCases
import com.suleyman.notesapp.ui.notes.NoteViewModel
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class NoteViewModelTest {


    @Test
    fun testNoteSave() {

        val wrapperUseCases = Mockito.mock(WrapperNotesUseCases::class.java)

        val noteViewModel = NoteViewModel(wrapperUseCases)

        val note = NoteEntity(0, "Test", "Test text", 0, false)

        noteViewModel.save(note)

    }


}