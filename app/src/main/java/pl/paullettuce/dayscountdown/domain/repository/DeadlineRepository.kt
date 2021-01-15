package pl.paullettuce.dayscountdown.domain.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.domain.model.DeadlineInfo
import pl.paullettuce.dayscountdown.storage.entity.DeadlineData

interface DeadlineRepository {
    fun saveDeadlineDatetime(datetimeTimestamp: Long): Completable
    fun getDeadlineInfo(): LiveData<DeadlineInfo>
    fun updateDeadlineDatetime(datetimeTimestamp: Long): Completable
    fun getDeadlineDataCount(): Single<Int>
}