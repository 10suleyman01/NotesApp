package com.suleyman.notesapp.ui.tasks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentListBinding
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.MutListTasks
import com.suleyman.notesapp.other.OnTaskClickListener
import com.suleyman.notesapp.ui.tasks.adapter.TasksAdapter
import kotlinx.coroutines.flow.collectLatest
import org.koin.android.ext.android.inject

class TasksFragment : Fragment(R.layout.fragment_list), View.OnClickListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: TasksAdapter

    private val createTaskDialog = CreateTaskDialogFragment()

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

    private fun taskClickListener() = object: OnTaskClickListener {
        override fun onTaskClick(task: TaskEntity) {

            val args = bundleOf("task" to task, "isEdit" to true)
            createTaskDialog.arguments = args
            createTaskDialog.show(parentFragmentManager, CreateTaskDialogFragment.TAG)
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
                createTaskDialog.show(parentFragmentManager, CreateTaskDialogFragment.TAG)
            }
        }
    }
}