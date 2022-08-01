package com.suleyman.notesapp.other

import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity

interface OnNoteClickListener {
    fun onNoteClick(note: NoteEntity)
}

interface OnTaskClickListener {
    fun onTaskClick(task: TaskEntity, index: Int)
    fun onTaskChecked(task: TaskEntity, index: Int)
}

interface TaskSaveHandle {
    fun saveTask(task: TaskEntity)
    fun deleteTask(task: TaskEntity, index: Int)
}