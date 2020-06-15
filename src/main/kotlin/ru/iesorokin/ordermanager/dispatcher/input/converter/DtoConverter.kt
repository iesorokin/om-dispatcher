package ru.iesorokin.ordermanager.dispatcher.input.converter

import ru.iesorokin.ordermanager.dispatcher.core.domain.CreationInformation
import ru.iesorokin.ordermanager.dispatcher.core.domain.Line
import ru.iesorokin.ordermanager.dispatcher.core.domain.Order
import ru.iesorokin.ordermanager.dispatcher.input.dto.LineRequest
import ru.iesorokin.ordermanager.dispatcher.input.dto.OrderRequest
import java.time.LocalDateTime

 fun OrderRequest.toOrder(): Order {
return Order(
        channel = this.channel,
        lines = this.lines.map { it.toLine() },
        creationInformation = CreationInformation(
                createdBy = "user",
                create = LocalDateTime.now()
        ),
        phone = this.phone
)
}

private fun LineRequest.toLine(): Line {
    return Line(
            status, type, quantity, amount
    )
}
