package com.suleyman.notesapp.other

import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity

interface OnNoteClickListener {
    fun onNoteClick(note: NoteEntity)
}

interface OnTaskClickListener {
    fun onTaskClick(task: TaskEntity)
}