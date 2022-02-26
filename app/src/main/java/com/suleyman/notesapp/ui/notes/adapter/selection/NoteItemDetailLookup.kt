package com.suleyman.notesapp.ui.notes.adapter.selection

import android.view.MotionEvent
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.ui.notes.adapter.NoteHolder

class NoteItemDetailLookup(
    private val recyclerView: RecyclerView
): ItemDetailsLookup<NoteEntity>() {

    override fun getItemDetails(e: MotionEvent): ItemDetails<NoteEntity>? {
        val view = recyclerView.findChildViewUnder(e.x, e.y)
        if (view != null) {
            return (recyclerView.getChildViewHolder(view) as NoteHolder).getItemDetails()
        }
        return null
    }
}