package com.suleyman.notesapp.ui.tasks

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.domain.usecase.tasks.WrapperTasksUseCases
import com.suleyman.notesapp.other.ListTasks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TasksViewModel(
    private val useCases: WrapperTasksUseCases
) : ViewModel() {

    private val _states: MutableStateFlow<TasksEvent> = MutableStateFlow(TasksEvent.None)
    var states = _states.asStateFlow()

    fun tasks() = loadingEvent {
        val tasks = useCases.getListTasksUseCase.execute()
        _states.value = TasksEvent.GetListTasks(tasks)
    }

    fun newOrUpdate(task: TaskEntity) = viewModelScope.launch {
        useCases.createAndSaveTaskUseCase.execute(task)
        _states.value = TasksEvent.AddNewTask(task)
    }

    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        useCases.deleteTaskUseCase.execute(task)
        _states.value = TasksEvent.Deleted
    }

    suspend fun getById(id: Long): TaskEntity {
        return useCases.getTaskByIdUseCase.execute(id)
    }

    private fun loadingEvent(delayInMillis: Long = 0, body: suspend () -> Unit) =
        viewModelScope.launch {
            _states.value = TasksEvent.Loading(true)
            if (delayInMillis > 0) {
                delay(delayInMillis)
            }
            body()
            _states.value = TasksEvent.Loading(false)
        }

    sealed class TasksEvent {
        object None : TasksEvent()
        object Deleted : TasksEvent()
        data class Loading(val isLoading: Boolean) : TasksEvent()
        data class GetListTasks(val tasks: ListTasks) : TasksEvent()
        data class AddNewTask(val task: TaskEntity) : TasksEvent()
    }

}