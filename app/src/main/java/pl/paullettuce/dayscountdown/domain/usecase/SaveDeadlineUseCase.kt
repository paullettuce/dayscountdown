package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Completable
import pl.paullettuce.dayscountdown.domain.repository.DeadlineRepository

interface SaveDeadlineUseCase {
    operator fun invoke(datetimeTimestamp: Long): Completable
}

class SaveDeadlineUseCaseImpl(
    private val deadlineRepository: DeadlineRepository,
    private val hasDataUseCase: HasDataUseCase
) : SaveDeadlineUseCase {
    override fun invoke(datetimeTimestamp: Long): Completable {
        return hasDataUseCase().flatMapCompletable { hasData ->
            if (hasData) {
                deadlineRepository.updateDeadlineDatetime(datetimeTimestamp)
            } else {
                deadlineRepository.saveDeadlineDatetime(datetimeTimestamp)
            }
        }
    }
}