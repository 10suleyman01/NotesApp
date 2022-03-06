package com.suleyman.notesapp.ui.tasks.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.TaskItemBinding
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.MutListTasks

class TasksAdapter : RecyclerView.Adapter<TaskHolder>() {

    val items: MutListTasks = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun addTask(task: TaskEntity) {
        items.add(task)
        notifyDataSetChanged()
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
            )
        )
    }

    override fun onBindViewHolder(holder: TaskHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount() = items.size
}