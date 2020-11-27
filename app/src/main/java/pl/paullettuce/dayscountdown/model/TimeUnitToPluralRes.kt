package pl.paullettuce.dayscountdown.model

import androidx.annotation.PluralsRes
import java.util.concurrent.TimeUnit


data class TimeUnitToPluralRes(
    val timeUnit: TimeUnit,
    @PluralsRes val resourceId: Int
)