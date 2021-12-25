package com.mercadolivro.enums

enum class Errors(val message: String, val code: String) {

    ML101(message = "Book [%s] not exists", code = "ML-101"),
    ML201(message = "Customer[%s] not exists", code = "ML-201" )

}