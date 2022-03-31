package com.suleyman.notesapp.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentListBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.MutListTasks
import com.suleyman.notesapp.other.OnTaskClickListener
import com.suleyman.notesapp.ui.MainActivity
import com.suleyman.notesapp.ui.tasks.adapter.selection.TaskItemDetailLookup
import com.suleyman.notesapp.ui.notes.adapter.selection.NoteItemKeyProvider
import com.suleyman.notesapp.ui.tasks.adapter.selection.TaskSelectionObserver
import com.suleyman.notesapp.ui.tasks.adapter.TasksAdapter
import com.suleyman.notesapp.ui.tasks.adapter.selection.TaskItemKeyProvider
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

private const val SELECTION_TASKS_ID = "tasks_selection_id"

class TasksFragment : Fragment(R.layout.fragment_list), View.OnClickListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: TasksAdapter

    private val createTaskDialog = CreateTaskDialogFragment()
    private var tracker: SelectionTracker<TaskEntity>? = null

    private val viewModel: TasksViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
        adapter = TasksAdapter()
        adapter.listener = taskClickListener()

        createTaskDialog.listener = createTaskListener()

        binding.apply {
            rvItems.layoutManager = LinearLayoutManager(requireContext())
            rvItems.adapter = adapter

            initSelectionTracker(binding.rvItems)

            fabNew.setOnClickListener(this@TasksFragment)
        }

        viewModel.tasks()

        lifecycleScope.launchWhenStarted {
            viewModel.states.collectLatest { event ->
                when (event) {
                    is TasksViewModel.TasksEvent.GetListTasks -> {
                        val tasks = event.tasks
                        adapter.setTasks(tasks as MutListTasks)
                    }
                    is TasksViewModel.TasksEvent.Loading -> {
                        binding.pbLoading.isVisible = event.isLoading
                    }
                    is TasksViewModel.TasksEvent.AddNewTask -> {
                        viewModel.tasks()
                    }
                    else -> TasksViewModel.TasksEvent.None
                }
            }

        }


    }

    private fun initSelectionTracker(rvItems: RecyclerView) {
        tracker = SelectionTracker.Builder(
            SELECTION_TASKS_ID,
            rvItems,
            TaskItemKeyProvider(adapter),
            TaskItemDetailLookup(rvItems),
            StorageStrategy.createParcelableStorage(TaskEntity::class.java),
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        tracker?.addObserver(TaskSelectionObserver(tracker, (activity as MainActivity)))
        adapter.tracker = tracker
    }

    private fun taskClickListener() = object: OnTaskClickListener {
        override fun onTaskClick(task: TaskEntity) {

            val args = bundleOf("task" to task)
            createTaskDialog.arguments = args
            createTaskDialog.show(parentFragmentManager, CreateTaskDialogFragment.TAG)
        }

        override fun onTaskChecked(task: TaskEntity) {
            viewModel.newTask(task)
        }
    }

    private fun createTaskListener() = object : CreateTaskDialogFragment.TaskSaveHandle {
        override fun saveTask(task: TaskEntity) {
            viewModel.newTask(task)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fabNew -> {
                createTaskDialog.arguments = null
                createTaskDialog.show(parentFragmentManager, CreateTaskDialogFragment.TAG)
            }
        }
    }
}