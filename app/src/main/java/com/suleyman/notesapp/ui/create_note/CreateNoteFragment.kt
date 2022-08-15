package com.suleyman.notesapp.ui.create_note

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentCreateNoteBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.text
import com.suleyman.notesapp.other.textWithTrim
import com.suleyman.notesapp.ui.MainActivity
import com.suleyman.notesapp.ui.notes.NotesFragmentArgs

const val RESULT_NOTE_KEY = "note_key"
const val NOTE = "note"

class CreateNoteFragment : Fragment(R.layout.fragment_create_note) {

    private var _binding: FragmentCreateNoteBinding? = null
    private val binding get() = _binding!!
    private val args: NotesFragmentArgs by navArgs()

    private var isEditNoteMode = false
    private var isBookmark = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menu: MenuHost = requireActivity()

        menu.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, inflater: MenuInflater) {
                inflater.inflate(R.menu.menu_create_note, menu)

                val item = menu.findItem(R.id.bookmark)
                setBookmarked(item)
            }

            override fun onMenuItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.save -> {
                        if (isEditNoteMode) {
                            val note = args.note
                            note?.let {
                                note.title = binding.etTitle.textWithTrim()
                                note.text = binding.etText.textWithTrim()
                                setNoteResult(note)
                                saveAndBack()
                            }
                            return true
                        }

                        val title = binding.etTitle.textWithTrim()
                        val text = binding.etText.textWithTrim()

                        if (title.isNotEmpty() && text.isNotEmpty()) {
                            val note =
                                NoteEntity(0, title, text, System.currentTimeMillis(), isBookmark)
                            setNoteResult(note)
                            saveAndBack()
                        }
                    }

                    R.id.bookmark -> {
                        if (isEditNoteMode) {
                            val note = args.note
                            note?.let {
                                it.isBookmarked = !it.isBookmarked
                            }
                            setBookmarked(item)
                        } else {
                            isBookmark = !isBookmark
                            item.setIcon(
                                if (isBookmark) R.drawable.ic_round_bookmark_24 else
                                    R.drawable.ic_baseline_bookmark_border_24
                            )
                        }
                    }

                    android.R.id.home -> {
                        (activity as MainActivity).onBackPressed()
                    }
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        _binding = FragmentCreateNoteBinding.bind(view)

        checkEditMode()
    }

    private fun checkEditMode() {
        val note = args.note

        if (note != null) {
            isEditNoteMode = true
            setNoteFields(note)
        }
    }

    private fun setNoteFields(note: NoteEntity) {
        binding.apply {
            etTitle.setText(note.title)
            etText.setText(note.text)
        }
    }

    private fun setBookmarked(item: MenuItem) {
        if (isEditNoteMode) {
            val note = args.note
            note?.let {
                item.setIcon(
                    if (it.isBookmarked) R.drawable.ic_round_bookmark_24 else
                        R.drawable.ic_baseline_bookmark_border_24
                )
            }
        } else {
            item.setIcon(R.drawable.ic_baseline_bookmark_border_24)
        }
    }

    private fun setNoteResult(note: NoteEntity) {
        val noteBundle = bundleOf(NOTE to note)
        setFragmentResult(RESULT_NOTE_KEY, noteBundle)
    }

    private fun saveAndBack() {
        val action = CreateNoteFragmentDirections.actionCreateNoteFragmentToNotesFragment()

        val navOptions = NavOptions.Builder()
            .setPopUpTo(R.id.notesFragment, true)
            .build()

        findNavController().navigate(action, navOptions)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}