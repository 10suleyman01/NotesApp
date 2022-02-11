package com.suleyman.notesapp.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class NoteModel(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val title: String,
    val text: String,
    val createdAt: Long,
    val isBookmarked: Boolean
): Parcelable {

    override fun toString(): String {
        return title
    }
}
