package com.suleyman.notesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.suleyman.notesapp.R
import com.suleyman.notesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.include.toolbar)

        val navController = findNavController(R.id.container)

        val appBarConfiguration =
            AppBarConfiguration(setOf(R.id.tasksFragment, R.id.notesFragment, R.id.profileFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.apply {
            bottomNavBar.setupWithNavController(navController)
        }
    }

    fun getToolbar() = binding.include.toolbar

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}