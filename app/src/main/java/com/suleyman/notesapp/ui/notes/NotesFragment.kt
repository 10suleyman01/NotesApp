package com.suleyman.notesapp.ui.notes

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentListBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.OnNoteClickListener
import com.suleyman.notesapp.ui.MainActivity
import com.suleyman.notesapp.ui.create_note.RESULT_NOTE_KEY
import com.suleyman.notesapp.ui.notes.adapter.NotesAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class NotesFragment : Fragment(R.layout.fragment_list), SearchView.OnQueryTextListener {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: NotesAdapter

    private val viewModel: NoteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
        adapter = NotesAdapter()
        setAdapterClickListener(adapter)

        binding.apply {
            rvItems.layoutManager = LinearLayoutManager(requireContext())
            rvItems.setHasFixedSize(true)
            rvItems.adapter = adapter

            fabNewNote.setOnClickListener {
                val action = NotesFragmentDirections.actionNotesFragmentToCreateNoteFragment()
                findNavController().navigate(action)
            }
        }

        viewModel.notes()

        setFragmentResultListener(RESULT_NOTE_KEY) { requestKey, bundle ->
            val note = bundle["note"] as NoteEntity
            lifecycleScope.launch {
                if (requestKey == RESULT_NOTE_KEY) {
                    viewModel.save(note)
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.states.collectLatest { event ->
                when (event) {

                    is NoteViewModel.NotesEvent.NewNote -> {
                        viewModel.notes()
                    }

                    is NoteViewModel.NotesEvent.Loading -> {
                        binding.pbLoading.isVisible = event.isLoading
                    }

                    is NoteViewModel.NotesEvent.GetNotes -> {
                        val notes = event.notes
                        binding.tvInfo.isVisible = notes.isEmpty()
                        adapter.setNotes(notes)
                    }

                    else -> NoteViewModel.NotesEvent.None
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_notes, menu)

        val searchItem = menu.findItem(R.id.searchView)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(this)

        searchView.setOnSearchClickListener {

        }

        searchView.setOnCloseListener {
            false
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryTextChange(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNotesByTitle(newText)
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun searchNotesByTitle(query: String) {
        val searchQuery = "%$query%"
        viewModel.search(searchQuery)
    }

    private fun setAdapterClickListener(adapter: NotesAdapter) {
        adapter.listener = object : OnNoteClickListener {
            override fun onNoteClick(note: NoteEntity) {
                val action = NotesFragmentDirections.actionNotesFragmentToCreateNoteFragment(note)
                findNavController().navigate(action)
            }
        }
    }

}