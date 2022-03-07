package com.suleyman.notesapp.other

import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.appbar.MaterialToolbar
import com.suleyman.notesapp.ui.MainActivity
import com.suleyman.notesapp.ui.notes.NoteViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


fun AppCompatEditText.text(): String {
    return text.toString()
}

fun AppCompatEditText.clearText() {
    setText("")
}

fun logDebug(msg: String) {
    Log.d("DEBUGER", msg)
}