package ru.iesorokin.ordermanager.dispatcher.output.stream.sender

import mu.KotlinLogging
import org.springframework.messaging.Message
import org.springframework.stereotype.Service
import ru.iesorokin.ordermanager.dispatcher.config.MessageQueueSource
import ru.iesorokin.ordermanager.dispatcher.core.domain.Order
import ru.iesorokin.ordermanager.dispatcher.error.SendMessageException
import ru.iesorokin.ordermanager.dispatcher.output.converter.ProcessMessageBuilder

private val log = KotlinLogging.logger {}

@Service
class OrchestratorSender(
        private val messageQueueSource: MessageQueueSource,
        private val processMessageBuilder: ProcessMessageBuilder
) {
    /**
     * @throws SendStartOrchestrationProcessMessageException describing the problem while sending pod message
     */
    fun sendStartProcessMessage(order: Order) {
        sendStartProcessMessage(
                processMessageBuilder.buildPodProcessMessage(order)
        )
    }

    private fun sendStartProcessMessage(message: Message<*>) {
        messageQueueSource.startOrchestrationProcess().sendOrThrow(message) { e ->
            SendMessageException("Message: $message. Exception: $e")
        }
        log.info { "Message was sent to startOrchestrationProcess exchange. $message" }
    }
}