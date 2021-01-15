package pl.paullettuce.dayscountdown.domain.model

import pl.paullettuce.dayscountdown.data.TimeLeft
import pl.paullettuce.dayscountdown.storage.entity.ReminderRepeatInterval

data class DeadlineInfo(
    val datetime: String,
    val timeLeft: TimeLeft,
    val reminderChecked: Boolean,
    val reminderRepeatInterval: ReminderRepeatInterval
) {
    companion object {
        val EMPTY = DeadlineInfo(
            "",
            TimeLeft.empty(),
            false,
            ReminderRepeatInterval.default()
        )
    }
}