package com.suleyman.notesapp.ui.tasks.adapter.selection

import androidx.recyclerview.selection.ItemKeyProvider
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.ui.notes.adapter.NotesAdapter
import com.suleyman.notesapp.ui.tasks.adapter.TasksAdapter

class TaskItemKeyProvider(
    private val adapter: TasksAdapter
): ItemKeyProvider<TaskEntity>(SCOPE_CACHED) {

    override fun getKey(position: Int): TaskEntity {
        return adapter.tasks[position]
    }

    override fun getPosition(key: TaskEntity): Int {
        return adapter.tasks.indexOf(key)
    }
}