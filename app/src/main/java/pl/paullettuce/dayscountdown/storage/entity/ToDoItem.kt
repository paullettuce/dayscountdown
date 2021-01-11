package pl.paullettuce.dayscountdown.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ToDoItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val text: String,
    var done: Boolean = false
)