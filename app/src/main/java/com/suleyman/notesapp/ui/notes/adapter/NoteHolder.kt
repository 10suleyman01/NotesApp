package com.suleyman.notesapp.ui.notes.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.NoteItemBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.DateFormatter
import com.suleyman.notesapp.other.ListNotes
import com.suleyman.notesapp.other.OnNoteClickListener
import com.suleyman.notesapp.other.DateType


class NoteHolder(
    private val notes: ListNotes,
    private val view: NoteItemBinding,
    private val listener: OnNoteClickListener?
) : RecyclerView.ViewHolder(view.root) {

    fun bind(note: NoteEntity, selected: Boolean = false) {
        view.apply {
            tvTitle.text = note.title
            tvText.text = note.text
            tvCreatedAt.text = DateFormatter.dateFromString(note.createdAt, DateType.Date)
            bookmark.isVisible = note.isBookmarked
            checked.isVisible = selected
        }
        view.holder.setOnClickListener {
            listener?.onNoteClick(note)
        }
    }

    fun getItemDetails(): ItemDetailsLookup.ItemDetails<NoteEntity> =
        object : ItemDetailsLookup.ItemDetails<NoteEntity>() {
            override fun getPosition(): Int = adapterPosition
            override fun getSelectionKey(): NoteEntity = notes[adapterPosition]
        }
}