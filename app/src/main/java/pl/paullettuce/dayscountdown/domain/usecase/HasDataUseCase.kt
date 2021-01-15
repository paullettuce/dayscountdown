package pl.paullettuce.dayscountdown.domain.usecase

import io.reactivex.rxjava3.core.Single
import pl.paullettuce.dayscountdown.domain.repository.DeadlineRepository

interface HasDataUseCase {
    operator fun invoke(): Single<Boolean>
}

class HasDataUseCaseImpl(
    private val deadlineRepository: DeadlineRepository
): HasDataUseCase {
    override fun invoke(): Single<Boolean> {
        return deadlineRepository.getDeadlineDataCount()
            .map { it > 0 }
    }
}