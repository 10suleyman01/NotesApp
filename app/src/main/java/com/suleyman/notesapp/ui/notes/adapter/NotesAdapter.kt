package com.suleyman.notesapp.ui.notes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.NoteItemBinding
import com.suleyman.notesapp.other.ListNotes
import com.suleyman.notesapp.other.MutListNotes

class NotesAdapter: RecyclerView.Adapter<NoteHolder>() {

    private val notes: MutListNotes? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setNotes(newNotes: ListNotes) {
        notes?.clear()
        notes?.addAll(newNotes)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        notes?.let {
            holder.bind(it[position])
        }
    }

    override fun getItemCount() = notes?.size ?: 0
}