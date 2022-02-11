package com.suleyman.notesapp.ui.notes

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentListBinding
import com.suleyman.notesapp.ui.notes.adapter.NotesAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotesFragment : Fragment(R.layout.fragment_list) {

    private lateinit var binding: FragmentListBinding
    private lateinit var adapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
        adapter = NotesAdapter()

        with(binding) {
            rvItems.setHasFixedSize(true)
            rvItems.layoutManager = LinearLayoutManager(requireContext())
            rvItems.adapter = adapter
        }
    }

}