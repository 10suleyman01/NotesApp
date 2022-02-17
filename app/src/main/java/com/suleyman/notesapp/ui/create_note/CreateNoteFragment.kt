package com.suleyman.notesapp.ui.create_note

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentCreateNoteBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.ui.MainActivity
import com.suleyman.notesapp.ui.notes.NotesFragmentArgs
import kotlinx.coroutines.launch

const val RESULT_NOTE_KEY = "note_key"
const val NOTE = "note"

class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {

    private lateinit var binding: FragmentCreateNoteBinding
    private val args: NotesFragmentArgs by navArgs()

    private var isEditNoteMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCreateNoteBinding.bind(view)

        checkEditMode()
    }

    private fun checkEditMode() {
        val note = args.note

        if (note != null) {
            isEditNoteMode = true

            Toast.makeText(requireContext(), "Edit Mode", Toast.LENGTH_SHORT).show()

            setNoteFields(note)
        }
    }

    private fun setNoteFields(note: NoteEntity) {
        binding.apply {
            etTitle.setText(note.title)
            etText.setText(note.text)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_create_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save -> {

                if (isEditNoteMode) {

                    Toast.makeText(requireContext(), "Saved in Edit Mode", Toast.LENGTH_SHORT)
                        .show()

                    val note = args.note

                    note?.let {
                        note.title = binding.etTitle.text.toString()
                        note.text = binding.etText.text.toString()

                        val noteBundle = bundleOf(NOTE to note)
                        setFragmentResult(RESULT_NOTE_KEY, noteBundle)
                        saveAndBack()
                    }

                    return true
                }

                Toast.makeText(requireContext(), "Not Edit Mode", Toast.LENGTH_SHORT).show()

                lifecycleScope.launch {

                    val title = binding.etTitle.text.toString()
                    val text = binding.etText.text.toString()

                    if (title.isNotEmpty() && text.isNotEmpty()) {
                        val note = NoteEntity(0, title, text, System.currentTimeMillis(), false)
                        val noteBundle = bundleOf(NOTE to note)
                        setFragmentResult(RESULT_NOTE_KEY, noteBundle)
                        saveAndBack()
                    }

                }
            }

            android.R.id.home -> {
                (activity as MainActivity).onBackPressed()
            }
        }
        return true
    }

    private fun saveAndBack() {
        val action = CreateNoteFragmentDirections.actionCreateNoteFragmentToNotesFragment()

        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.notesFragment, true)
            .build()

        findNavController().navigate(action, navOptions)
    }

}