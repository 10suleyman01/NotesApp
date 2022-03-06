package com.suleyman.notesapp.ui.tasks

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentListBinding
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.ui.tasks.adapter.TasksAdapter
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

        createTaskDialog.listener = createTaskListener()


        binding.apply {
            rvItems.layoutManager = LinearLayoutManager(requireContext())
            rvItems.adapter = adapter

            fabNew.setOnClickListener(this@TasksFragment)

        }

    }

    private fun createTaskListener() = object : CreateTaskDialogFragment.TaskSaveHandle {
        override fun saveTask(task: TaskEntity) {
//            viewModel.save(task)
//            adapter.addTask(task)
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