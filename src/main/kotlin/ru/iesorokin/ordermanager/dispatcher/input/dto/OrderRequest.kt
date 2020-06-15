package ru.iesorokin.ordermanager.dispatcher.input.dto

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.time.LocalDateTime

data class OrderRequest(
        val channel: String,
        val createdBy: String,
        val phone: String,
        val lines: Collection<LineRequest>
)

data class LineRequest(
        val status: String,
        val type: String,
        val quantity: BigDecimal,
        val amount: BigDecimal
)