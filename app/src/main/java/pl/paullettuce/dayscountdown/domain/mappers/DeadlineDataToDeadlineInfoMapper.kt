package pl.paullettuce.dayscountdown.domain.mappers

import pl.paullettuce.dayscountdown.commons.TimeFormatter
import pl.paullettuce.dayscountdown.data.TimeLeft
import pl.paullettuce.dayscountdown.domain.model.DeadlineInfo
import pl.paullettuce.dayscountdown.storage.entity.DeadlineData

class DeadlineDataToDeadlineInfoMapper : Mapper<DeadlineData?, DeadlineInfo> {
    override fun map(input: DeadlineData?): DeadlineInfo {
        return if (input == null) {
            DeadlineInfo.EMPTY
        } else {
            DeadlineInfo(
                TimeFormatter.friendlyFromMillis(input.datetimeTimestamp),
                TimeLeft.betweenNowAndTimestamp(input.datetimeTimestamp),
                input.reminderNotificationEnabled,
                input.reminderRepeatInterval
            )
        }
    }
}