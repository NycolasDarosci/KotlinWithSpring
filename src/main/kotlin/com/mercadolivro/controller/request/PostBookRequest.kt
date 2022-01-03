package com.mercadolivro.controller.request

import com.fasterxml.jackson.annotation.JsonAlias
import com.mercadolivro.enums.BookStatus
import java.math.BigDecimal
import javax.validation.constraints.NotEmpty

data class PostBookRequest(
    @field:NotEmpty(message = "name deve ser informado")
    var name: String,

    @field:NotEmpty(message = "price deve ser informado")
    var price: BigDecimal,

    @JsonAlias("customer_id")
    var customerId: Int,
)
