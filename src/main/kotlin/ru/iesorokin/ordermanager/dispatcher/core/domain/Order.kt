package ru.iesorokin.ordermanager.dispatcher.core.domain

import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*

@Document
data class Order(
        val id: UUID = UUID.randomUUID(),
        val channel: String,
        @Version
        val documentVersion: Int? = 0,
        val phone: String,
        val lines: Collection<Line>,
        val creationInformation: CreationInformation

)

data class CreationInformation(
        val createdBy: String,
        val create: LocalDateTime
)


data class Line(
        val status: String,
        val type: String,
        val quantity: BigDecimal,
        val amount: BigDecimal
)