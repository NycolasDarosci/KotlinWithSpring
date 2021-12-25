package com.mercadolivro.exception.model

data class FieldErrorResponse(
    var message: String,
    var field: String
)
