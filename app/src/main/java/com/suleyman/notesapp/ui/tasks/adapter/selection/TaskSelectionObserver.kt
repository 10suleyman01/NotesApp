package com.suleyman.notesapp.ui.tasks.adapter.selection

import androidx.recyclerview.selection.SelectionTracker
import com.suleyman.notesapp.R
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.ui.MainActivity

class TaskSelectionObserver(
    private val tracker: SelectionTracker<TaskEntity>?,
    private val activity: MainActivity
) : SelectionTracker.SelectionObserver<TaskEntity>() {

    override fun onSelectionChanged() {
        super.onSelectionChanged()

        tracker?.let { selectionTracker ->
            activity.getToolbar().apply {
                if (selectionTracker.hasSelection()) {
                    title = "${activity.getString(R.string.selected)}: ${selectionTracker.selection.size()}"
                } else {
                    setTitle(R.string.tasks)
                }
            }
        }
    }

}