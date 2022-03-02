package com.suleyman.notesapp.other

import android.util.Log
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.suleyman.notesapp.ui.MainActivity


fun AppCompatEditText.text(): String {
    return text.toString()
}

fun Fragment.requireToolbar(): Toolbar {
    return (activity as MainActivity).getToolbar()
}

fun logDebug(msg: String) {
    Log.d("DEBUGER", msg)
}
