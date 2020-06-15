package ru.iesorokin.ordermanager.dispatcher.output.dto

data class StartOrchestrationProcessMessage(
        val businessProcessType: String,
        val businessKey: String,
        val variables: Map<String, Any>?
)