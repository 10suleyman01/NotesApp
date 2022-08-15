package com.suleyman.notesapp.app

import android.app.Application
import com.suleyman.notesapp.di.databaseModule
import com.suleyman.notesapp.di.notesUseCasesModule
import com.suleyman.notesapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NotesApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NotesApp)
            modules(listOf(databaseModule, notesUseCasesModule, viewModelModule))
        }
    }
}