package com.suleyman.notesapp.ui.tasks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.TaskItemBinding
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.DateFormatter
import com.suleyman.notesapp.other.OnTaskClickListener

class TaskHolder(
    private var item: TaskItemBinding,
    private var listener: OnTaskClickListener
): RecyclerView.ViewHolder(item.root) {

    fun bind(taskEntity: TaskEntity) {
        item.apply {
            cbTitle.text = taskEntity.title
            cbTitle.isChecked = taskEntity.completed
            tvCreatedTask.text = DateFormatter.dateFromString(taskEntity.createdAt, DateFormatter.FormatType.Date)
        }

        item.holder.setOnClickListener {
            listener.onTaskClick(taskEntity)
        }
    }

}