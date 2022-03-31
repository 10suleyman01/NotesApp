package com.suleyman.notesapp.ui.tasks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.CreateTaskModalDialogBinding
import com.suleyman.notesapp.domain.entity.TaskEntity
import com.suleyman.notesapp.other.clearText
import com.suleyman.notesapp.other.text
import org.koin.android.ext.android.inject

class CreateTaskDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {

    companion object {
        const val TAG = "CREATE_TASK_DIALOG"
    }

    private var _binding: CreateTaskModalDialogBinding? = null

    private val binding get() = _binding!!
    private val viewModel: TasksViewModel by inject()

    var listener: TaskSaveHandle? = null
    var task: TaskEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NORMAL, R.style.DialogStyle)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateTaskModalDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog?.setOnShowListener {

            task = arguments?.get("task") as TaskEntity?

            if (task != null) {
                binding.etTaskTitle.setText(task?.title)
                binding.cbCompleted.isChecked = task?.completed ?: false
            } else {
                binding.etTaskTitle.clearText()
            }

            BottomSheetBehavior.from(binding.content).apply {
                isFitToContents = false
                state = BottomSheetBehavior.STATE_EXPANDED
                saveFlags = BottomSheetBehavior.SAVE_ALL
            }
        }

        binding.apply {
            saveTask.setOnClickListener(this@CreateTaskDialogFragment)
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.saveTask -> if (listener != null) {

                if (task != null) {
                    task?.title = binding.etTaskTitle.text()
                    task?.completed = binding.cbCompleted.isChecked
                    listener?.saveTask(task!!)
                } else {
                    val title = binding.etTaskTitle.text()
                    val task = TaskEntity(0, title, System.currentTimeMillis(), binding.cbCompleted.isChecked)
                    listener?.saveTask(task)

                    binding.cbCompleted.isChecked = false
                    binding.etTaskTitle.clearText()
                }

                dialog?.dismiss()
            }
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    interface TaskSaveHandle {
        fun saveTask(task: TaskEntity)
    }

}