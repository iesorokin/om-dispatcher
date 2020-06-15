package ru.iesorokin.ordermanager.dispatcher.output.converter

import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import ru.iesorokin.ordermanager.dispatcher.core.domain.Order
import ru.iesorokin.ordermanager.dispatcher.output.dto.StartOrchestrationProcessMessage

@Component
class ProcessMessageBuilder {
    fun buildPodProcessMessage(order: Order): Message<*> {
        val payload = StartOrchestrationProcessMessage(
                businessKey = order.phone,
                businessProcessType = "manager",
                variables = mapOf("LINES" to order.lines)
        )
        return buildPayload(payload)
    }

    private fun buildPayload(startOrchestrationProcessMessage: StartOrchestrationProcessMessage): Message<*> =
            MessageBuilder
                    .withPayload(startOrchestrationProcessMessage)
                    .build()


}

