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

class CreateTaskDialogFragment : BottomSheetDialogFragment(), View.OnClickListener {

    companion object {
        const val TAG = "CREATE_TASK_DIALOG"
    }

    private var _binding: CreateTaskModalDialogBinding? = null
    private val binding get() = _binding!!

    var listener: TaskSaveHandle? = null

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
                val title = binding.etTaskTitle.text()
                if (title.isNotEmpty()) {
                    val task = TaskEntity(0, title, System.currentTimeMillis(),false)
                    listener?.saveTask(task)
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