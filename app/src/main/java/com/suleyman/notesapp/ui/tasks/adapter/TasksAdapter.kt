package com.suleyman.notesapp.ui.tasks.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Index
import com.suleyman.notesapp.databinding.TaskItemBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.MutListTasks
import com.suleyman.notesapp.other.OnTaskClickListener
import okhttp3.internal.concurrent.Task

class TasksAdapter: RecyclerView.Adapter<TaskHolder>() {

    val tasks: MutListTasks = mutableListOf()
    var listener: OnTaskClickListener? = null
    var tracker: SelectionTracker<TaskEntity>? = null

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(tasks: MutListTasks) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }

    fun getIndexById(id: Long) = tasks.indexOf(
        tasks.first { it.id == id }
    )

    fun deleteTask(task: TaskEntity, position: Int) {
        tasks.remove(task)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {

        return TaskHolder(
            tasks,
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener!!
        )
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        val task = tasks[position]
        holder.bind(task, tracker!!.isSelected(task))
    }

    override fun getItemCount() = tasks.size
}