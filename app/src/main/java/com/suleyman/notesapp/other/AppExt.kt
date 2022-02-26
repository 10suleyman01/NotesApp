package com.suleyman.notesapp.other

import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar
import com.suleyman.notesapp.ui.MainActivity


fun AppCompatEditText.text(): String {
    return text.toString()
}
