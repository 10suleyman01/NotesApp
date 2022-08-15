package com.suleyman.notesapp.other

import com.suleyman.notesapp.domain.entity.NoteEntity

interface OnNoteClickListener {
    fun onNoteClick(note: NoteEntity)
}