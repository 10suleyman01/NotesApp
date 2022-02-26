package com.suleyman.notesapp.ui.notes.adapter.selection

import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.selection.SelectionTracker
import com.google.android.material.appbar.MaterialToolbar
import com.suleyman.notesapp.R
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.ui.MainActivity

class NotesSelectionObserver(
    private val tracker: SelectionTracker<NoteEntity>?,
    private val activity: MainActivity
) : SelectionTracker.SelectionObserver<NoteEntity>() {

    override fun onSelectionChanged() {
        super.onSelectionChanged()

        tracker?.let { selectionTracker ->
            activity.getToolbar().apply {
                if (selectionTracker.hasSelection()) {
                    title = "${activity.getString(R.string.selected)}: ${selectionTracker.selection.size()}"
                    menu.findItem(R.id.deleteNote).isVisible = true
                    menu.findItem(R.id.searchView).isVisible = false
                } else {
                    setTitle(R.string.notes)
                    menu.findItem(R.id.deleteNote).isVisible = false
                    menu.findItem(R.id.searchView).isVisible = true
                }
            }
        }
    }

}