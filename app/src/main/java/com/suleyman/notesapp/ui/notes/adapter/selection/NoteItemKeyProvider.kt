package com.suleyman.notesapp.ui.notes.adapter.selection

import androidx.recyclerview.selection.ItemKeyProvider
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.ui.notes.adapter.NotesAdapter

class NoteItemKeyProvider(
    private val adapter: NotesAdapter
): ItemKeyProvider<NoteEntity>(SCOPE_CACHED) {

    override fun getKey(position: Int): NoteEntity? {
        return adapter.notes[position]
    }

    override fun getPosition(key: NoteEntity): Int {
        return adapter.notes.indexOf(key)
    }
}