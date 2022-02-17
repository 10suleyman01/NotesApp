package com.suleyman.notesapp.ui.notes.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.NoteItemBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.DateFormatter
import com.suleyman.notesapp.other.OnNoteClickListener
import com.suleyman.notesapp.other.Type


class NoteHolder(
    private val view: NoteItemBinding,
    private val listener: OnNoteClickListener?
) : RecyclerView.ViewHolder(view.root) {

    fun bind(note: NoteEntity) {
        view.apply {
            tvTitle.text = note.title
            tvText.text = note.text
            tvCreatedAt.text = DateFormatter.dateFromString(note.createdAt, Type.Date)
            bookmark.isVisible = note.isBookmarked
        }
        view.holder.setOnClickListener {
            listener?.onNoteClick(note)
        }
    }
}