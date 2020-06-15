package ru.iesorokin.ordermanager.dispatcher.error

enum class ErrorCode(val code: Int, val errorMessage: String) {
    UNEXPECTED(201, "unexpected.error"),
    INVALID_ATTRIBUTE(103, "invalid.parameter")
}
