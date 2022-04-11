package com.suleyman.notesapp.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentProfileBinding
import com.suleyman.notesapp.ui.auth.AuthActivity

const val RC_SIGN_IN = "sign_in"

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

    }

    override fun onStart() {
        super.onStart()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        binding.apply {
            btnAuth.setOnClickListener(this@ProfileFragment)
        }
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnAuth -> {

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.profile_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {

            }
        }
        return true
    }
}