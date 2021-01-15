package pl.paullettuce.dayscountdown.storage.repository

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import pl.paullettuce.dayscountdown.domain.mappers.DeadlineDataToDeadlineInfoMapper
import pl.paullettuce.dayscountdown.domain.mappers.map
import pl.paullettuce.dayscountdown.domain.model.DeadlineInfo
import pl.paullettuce.dayscountdown.domain.repository.DeadlineRepository
import pl.paullettuce.dayscountdown.storage.dao.DeadlineDao
import pl.paullettuce.dayscountdown.storage.entity.DeadlineData

class DeadlineRepositoryImpl(
    private val deadlineDao: DeadlineDao,
    private val deadlineDataToDeadlineInfoMapper: DeadlineDataToDeadlineInfoMapper
) : DeadlineRepository {

    override fun getDeadlineInfo(): LiveData<DeadlineInfo> {
        return deadlineDao
            .getDeadlineData()
            .map {
                deadlineDataToDeadlineInfoMapper.map(it)
            }
    }

    override fun getDeadlineDataCount(): Single<Int> {
        return deadlineDao.getDeadlineDataCount()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun saveDeadlineDatetime(datetimeTimestamp: Long): Completable {
        return deadlineDao.insert(
            DeadlineData(datetimeTimestamp)
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateDeadlineDatetime(datetimeTimestamp: Long): Completable {
        return deadlineDao.update(datetimeTimestamp)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}