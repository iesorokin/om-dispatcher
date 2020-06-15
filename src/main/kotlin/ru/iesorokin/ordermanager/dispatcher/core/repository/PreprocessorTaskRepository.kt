package ru.iesorokin.ordermanager.dispatcher.core.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.iesorokin.ordermanager.dispatcher.core.domain.Order
import ru.iesorokin.ordermanager.dispatcher.input.dto.OrderRequest

interface PreprocessorTaskRepository : MongoRepository<Order, String>