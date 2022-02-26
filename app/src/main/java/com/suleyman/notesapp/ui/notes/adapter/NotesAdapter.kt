package com.suleyman.notesapp.ui.notes.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.NoteItemBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.ListNotes
import com.suleyman.notesapp.other.MutListNotes
import com.suleyman.notesapp.other.OnNoteClickListener

class NotesAdapter(): RecyclerView.Adapter<NoteHolder>() {

    val notes: MutListNotes = mutableListOf()
    var listener: OnNoteClickListener? = null
    var tracker: SelectionTracker<NoteEntity>? = null

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNotes(newNotes: ListNotes) {
        notes.clear()
        notes.addAll(newNotes)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addNote(note: NoteEntity) {
        notes.add(note)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        return NoteHolder(
            notes,
            NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            listener = listener
        )
    }

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        notes.let {
            val note = it[position]
            holder.bind(note, tracker!!.isSelected(note))
        }
    }

    override fun getItemId(position: Int) = position.toLong()

    override fun getItemViewType(position: Int) = position

    override fun getItemCount() = notes.size


}