package com.suleyman.notesapp.ui.profile

import android.content.Context
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContract
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.Api
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class GoogleSignContract(
    private val googleSignInClient: GoogleSignInClient
): ActivityResultContract<String, GoogleSignInAccount?>() {

    override fun createIntent(context: Context, input: String): Intent {
        return googleSignInClient.signInIntent
    }

    override fun parseResult(resultCode: Int, data: Intent?): GoogleSignInAccount? {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            return task.getResult(ApiException::class.java)
        } catch (e: ApiException) {
            e.printStackTrace()
        }
        return null
    }
}