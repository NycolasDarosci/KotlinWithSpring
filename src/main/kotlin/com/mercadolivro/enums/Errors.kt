package com.mercadolivro.enums

enum class Errors(val message: String, val code: String) {

    ML001(message = "invalid request", code = "ML-001"),
    ML101(message = "Book [%s] not exists", code = "ML-101"),
    ML102(message = "Cannot update book with status [%s]", code = "ML-102"),
    ML201(message = "Customer[%s] not exists", code = "ML-201" )

}