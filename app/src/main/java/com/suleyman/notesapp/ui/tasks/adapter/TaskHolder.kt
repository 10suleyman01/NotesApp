package com.suleyman.notesapp.ui.tasks.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.selection.ItemDetailsLookup
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.databinding.TaskItemBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.DateFormatter
import com.suleyman.notesapp.other.ListTasks
import com.suleyman.notesapp.other.OnTaskClickListener

class TaskHolder(
    private var tasks: ListTasks,
    private var item: TaskItemBinding,
    private var listener: OnTaskClickListener
): RecyclerView.ViewHolder(item.root) {

    fun bind(taskEntity: TaskEntity, selected: Boolean) {
        item.apply {
            cbTitle.text = taskEntity.title
            cbTitle.isChecked = taskEntity.completed
            tvCreatedTask.text = DateFormatter.dateFromString(taskEntity.createdAt, DateFormatter.FormatType.Date)

            taskIsSelected.isVisible = selected

            cbTitle.setOnCheckedChangeListener { _, isChecked ->
                taskEntity.completed = isChecked
                listener.onTaskChecked(taskEntity)
            }

        }

        item.holder.setOnClickListener {
            listener.onTaskClick(taskEntity)
        }
    }

    fun getItemDetails(): ItemDetailsLookup.ItemDetails<TaskEntity> =
        object : ItemDetailsLookup.ItemDetails<TaskEntity>() {
            override fun getPosition(): Int = adapterPosition
            override fun getSelectionKey(): TaskEntity = tasks[adapterPosition]
        }

}