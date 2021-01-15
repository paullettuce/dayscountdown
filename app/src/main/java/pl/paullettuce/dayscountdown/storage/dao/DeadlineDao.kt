package pl.paullettuce.dayscountdown.storage.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.storage.entity.DeadlineData

@Dao
interface DeadlineDao {

    @Query("SELECT * FROM deadlinedata")
    fun getDeadlineData(): LiveData<DeadlineData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(deadlineData: DeadlineData): Completable

    @Query("UPDATE deadlinedata SET datetimeTimestamp=:datetimeTimestamp")
    fun update(datetimeTimestamp: Long): Completable

    @Query("SELECT COUNT(*) FROM deadlinedata")
    fun getDeadlineDataCount(): Single<Int>
}