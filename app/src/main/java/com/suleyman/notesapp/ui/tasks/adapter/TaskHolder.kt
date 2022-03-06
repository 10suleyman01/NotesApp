package com.suleyman.notesapp.ui.tasks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.TaskItemBinding
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.DateFormatter

class TaskHolder(
    private var item: TaskItemBinding
): RecyclerView.ViewHolder(item.root) {

    fun bind(taskEntity: TaskEntity) {
        item.apply {
            cbTitle.text = taskEntity.title
            cbTitle.isChecked = taskEntity.completed
            tvCreatedTask.text = DateFormatter.dateFromString(taskEntity.createdAt, DateFormatter.FormatType.Date)
        }
    }

}