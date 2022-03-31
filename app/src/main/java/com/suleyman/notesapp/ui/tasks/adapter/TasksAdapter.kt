package com.suleyman.notesapp.ui.tasks.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.TaskItemBinding
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.MutListTasks
import com.suleyman.notesapp.other.OnTaskClickListener

class TasksAdapter: RecyclerView.Adapter<TaskHolder>() {

    private val items: MutListTasks = mutableListOf()
    var listener: OnTaskClickListener? = null

    init {
        setHasStableIds(true)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setTasks(tasks: MutListTasks) {
        items.clear()
        items.addAll(tasks)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskHolder {

        return TaskHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener!!
        )
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount() = items.size
}