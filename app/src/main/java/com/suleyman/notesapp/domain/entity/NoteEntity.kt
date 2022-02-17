package com.suleyman.notesapp.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) var id: Long,
    var title: String,
    var text: String,
    val createdAt: Long,
    val isBookmarked: Boolean
): Parcelable {
    override fun toString(): String {
        return title
    }
}
