package ru.iesorokin.ordermanager.dispatcher.config

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.SubscribableChannel

internal const val START_ORCHESTRATION_PROCESS = "startOrchestrationProcess"

interface MessageQueueSource {
    // Output
    @Output(START_ORCHESTRATION_PROCESS)
    fun startOrchestrationProcess(): SubscribableChannel

}

