package ru.iesorokin.ordermanager.dispatcher.input.web.controller

import io.swagger.annotations.ApiOperation
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.iesorokin.ordermanager.dispatcher.core.service.preprocessing.PreprocessingService
import ru.iesorokin.ordermanager.dispatcher.input.converter.toOrder
import ru.iesorokin.ordermanager.dispatcher.input.dto.OrderRequest
import javax.validation.Valid

@RestController
@RequestMapping("v1")
class OrderController(
        private val preproseccingService: PreprocessingService
) {

    @ApiOperation("Create order")
    @PostMapping("order", produces = [APPLICATION_JSON_VALUE])
    fun startGiveAway(@RequestBody @Valid request: OrderRequest): String {
        val order = request.toOrder()
        return preproseccingService.create(order)
    }
}

