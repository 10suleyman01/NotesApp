package com.suleyman.notesapp.ui.notes

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.selection.SelectionPredicates
import androidx.recyclerview.selection.SelectionTracker
import androidx.recyclerview.selection.StorageStrategy
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentListBinding
import com.suleyman.notesapp.domain.entity.NoteEntity
import com.suleyman.notesapp.other.OnNoteClickListener
import com.suleyman.notesapp.ui.MainActivity
import com.suleyman.notesapp.ui.create_note.NOTE
import com.suleyman.notesapp.ui.create_note.RESULT_NOTE_KEY
import com.suleyman.notesapp.ui.notes.adapter.NotesAdapter
import com.suleyman.notesapp.ui.notes.adapter.selection.NoteItemDetailLookup
import com.suleyman.notesapp.ui.notes.adapter.selection.NoteItemKeyProvider
import com.suleyman.notesapp.ui.notes.adapter.selection.NotesSelectionObserver
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val SELECTION_ID = "notes_selection_id"

class NotesFragment : Fragment(), SearchView.OnQueryTextListener {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: NoteViewModel by viewModel()
    private var tracker: SelectionTracker<NoteEntity>? = null

    private lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentListBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            val note = bundle[NOTE] as NoteEntity
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

                    is NoteViewModel.NotesEvent.Deleted -> {
                        viewModel.notes()
                    }

                    else -> NoteViewModel.NotesEvent.None
                }
            }
        }

        initSelectionTracker(binding.rvItems)
    }

    private fun initSelectionTracker(rvItems: RecyclerView) {
        tracker = SelectionTracker.Builder(
            SELECTION_ID,
            rvItems,
            NoteItemKeyProvider(adapter),
            NoteItemDetailLookup(rvItems),
            StorageStrategy.createParcelableStorage(NoteEntity::class.java),
        ).withSelectionPredicate(
            SelectionPredicates.createSelectAnything()
        ).build()

        tracker?.addObserver(NotesSelectionObserver(tracker, (activity as MainActivity)))
        adapter.tracker = tracker
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_notes, menu)

        val searchItem = menu.findItem(R.id.searchView)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(this)

        searchView.setOnSearchClickListener {
            // TODO 1 NOT IMPLEMENTED
        }

        searchView.setOnCloseListener {
            // TODO 2 NOT IMPLEMENTED
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
            R.id.deleteNote -> {
                tracker?.let {
                    val iterator = it.selection.toMutableList().iterator()
                    while (iterator.hasNext()) {
                        val noteModel = iterator.next()
                        viewModel.delete(noteModel)
                        iterator.remove()
                        tracker?.clearSelection()
                    }
                }
                true
            }
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