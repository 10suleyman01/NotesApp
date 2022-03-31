package com.suleyman.notesapp.ui.tasks.adapter.selection

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.ui.notes.adapter.NoteHolder
import com.suleyman.notesapp.ui.tasks.adapter.TaskHolder

class TaskItemDetailLookup(
    private val recyclerView: RecyclerView
): ItemDetailsLookup<TaskEntity>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<TaskEntity>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as TaskHolder).getItemDetails()
        }
        return null
    }
}