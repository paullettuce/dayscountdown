package pl.paullettuce.dayscountdown.domain.usecase

import androidx.lifecycle.LiveData
import pl.paullettuce.dayscountdown.domain.model.DeadlineInfo
import pl.paullettuce.dayscountdown.domain.repository.DeadlineRepository

interface GetDeadlineInfoUseCase {
    operator fun invoke(): LiveData<DeadlineInfo>
}

class GetDeadlineInfoUseCaseImpl(
    private val deadlineRepository: DeadlineRepository
): GetDeadlineInfoUseCase {
    override fun invoke() = deadlineRepository.getDeadlineInfo()
}