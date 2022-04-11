package com.suleyman.notesapp.ui.auth

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import com.suleyman.notesapp.databinding.ActivityAuthBinding

class AuthActivity: AppCompatActivity() {

    private var _binding: ActivityAuthBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityAuthBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.apply {

        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}