package com.suleyman.notesapp.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val firebaseModule = module {

    single {
        FirebaseDatabase.getInstance()
    }

    single {
        FirebaseAuth.getInstance()
    }

}