package ru.iesorokin.ordermanager.dispatcher.core.service.preprocessing

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.iesorokin.ordermanager.dispatcher.core.domain.Order
import ru.iesorokin.ordermanager.dispatcher.core.repository.PreprocessorTaskRepository
import ru.iesorokin.ordermanager.dispatcher.output.stream.sender.OrchestratorSender

private val log = KotlinLogging.logger {}

@Service
class PreprocessingService(
        private val preprocessorTaskRepository: PreprocessorTaskRepository,
        private val orchestratorSender: OrchestratorSender
) {

    fun create(order: Order): String{
        return preprocessorTaskRepository.save(order).id.toString()
    }

    fun sendOrchestrationProcess(order: Order) {
        orchestratorSender.sendStartProcessMessage(order)
    }
}
