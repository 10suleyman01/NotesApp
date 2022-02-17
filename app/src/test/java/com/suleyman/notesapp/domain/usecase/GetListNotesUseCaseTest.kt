package com.suleyman.notesapp.domain.usecase

import com.suleyman.notesapp.data.DatabaseNotesRepository
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.ListNotes
import junit.framework.TestCase
import org.junit.Test
import org.mockito.Mockito.*;

class GetListNotesUseCaseTest : TestCase() {


    @Test
    suspend fun `notes size must be more than 0` () {

        val notes: ListNotes = listOf(NoteEntity(0, "", "", 0, false))

        val repository = mock(DatabaseNotesRepository::class.java)
        `when`(repository.notes()).thenReturn(notes)
    }

}