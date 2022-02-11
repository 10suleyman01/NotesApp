package com.suleyman.notesapp.ui.notes.adapter

import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.NoteItemBinding
import com.suleyman.notesapp.model.NoteModel

class NoteHolder(private val view: NoteItemBinding): RecyclerView.ViewHolder(view.root) {

    fun bind(note: NoteModel) {
        view.apply {
            tvTitle.text = note.text
            tvText.text = note.text
            tvCreatedAt.text = note.createdAt.toString()
        }
    }

}