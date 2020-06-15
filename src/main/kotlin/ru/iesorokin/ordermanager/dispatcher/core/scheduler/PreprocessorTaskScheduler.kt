package ru.iesorokin.ordermanager.dispatcher.core.scheduler

import mu.KotlinLogging
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ru.iesorokin.ordermanager.dispatcher.core.domain.Order
import ru.iesorokin.ordermanager.dispatcher.core.repository.PreprocessorTaskRepository
import ru.iesorokin.ordermanager.dispatcher.core.service.preprocessing.PreprocessingService
import ru.iesorokin.ordermanager.dispatcher.input.dto.OrderRequest
import java.time.LocalDateTime

private val log = KotlinLogging.logger {}

@Service
@RefreshScope
class PreprocessorTaskScheduler(
        private val preprocessorTaskRepository: PreprocessorTaskRepository,
        private val preprocessingService: PreprocessingService,
        @param:Value("\${schedule.dispatcher.minutesForSelfProcessing}")
        private val minutesForSelfProcessing: Long
) {
    @SchedulerLock(
            name = "dispatcher",
            lockAtLeastFor = "\${schedule.dispatcher.lockAtLeast}",
            lockAtMostFor = "\${schedule.dispatcher.lockAtMost}"
    )
    @Scheduled(cron = "\${schedule.dispatcher.cron}", zone = "UTC")
    fun schedule() {
        val failedMessages = preprocessorTaskRepository.findAll()

        if (failedMessages.isNotEmpty()) {
            notify(failedMessages)

            failedMessages.forEach {
                preprocessingService.sendOrchestrationProcess(it)
            }
        }
    }

    private fun notify(failedMessages: List<Order>) {
        log.info { "Start scheduling for dispatcher tasks: $failedMessages" }

        val oldMessages = failedMessages.filter {
            it.creationInformation.create.isBefore(LocalDateTime.now().minusMinutes(minutesForSelfProcessing))
        }
        if (oldMessages.isNotEmpty()) {
            log.error { "Some tasks can't be processed too long. Messages: $oldMessages" }
        }
    }
}