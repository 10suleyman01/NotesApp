package com.suleyman.notesapp.ui.profile

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.FragmentProfileBinding
import com.suleyman.notesapp.other.requireToolbar
import org.koin.android.ext.android.inject

const val RC_SIGN_IN = "sign_in"

class ProfileFragment : Fragment(R.layout.fragment_profile), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var googleSignClient: GoogleSignInClient
    private lateinit var registerSignIn: ActivityResultLauncher<String>

    private val firebaseAuth: FirebaseAuth by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        googleSignClient = GoogleSignIn.getClient(requireContext(), gso)

        registerSignIn = registerForActivityResult(GoogleSignContract(googleSignClient)) { account ->
                account?.idToken?.let { id ->
                    firebaseAuthWithGoogle(id)
                }
            }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = firebaseAuth.currentUser
        updateUI(currentUser)
    }

    /*
    *  TODO 1: Set Avatar In Toolbar
    *  TODO 2:
    * */
    private fun updateUI(currentUser: FirebaseUser?) {
        val isNotAuth = currentUser == null
        binding.btnAuth.isVisible = isNotAuth
        requireToolbar().subtitle = if (isNotAuth) "Не авторизован" else "Авторизован"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        binding.apply {
            btnAuth.setOnClickListener(this@ProfileFragment)
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    updateUI(firebaseAuth.currentUser)
                } else {
                    updateUI(null)
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
                firebaseAuth.signOut()
                updateUI(null)
            }
        }
        return true
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btnAuth -> {
                registerSignIn.launch(RC_SIGN_IN)
            }
        }
    }


}