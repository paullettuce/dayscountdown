package pl.paullettuce.dayscountdown.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todoitem")
class TodoItem(
    val text: String,
    var done: Boolean = false
) {
    @PrimaryKey(autoGenerate = true) var id: Long = 0
}