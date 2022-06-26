package com.mercadolivro.controller.request

import com.mercadolivro.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest (

    @field:NotEmpty(message = "name deve ser informado")
    var name: String,

    @field:Email(message = "email deve ser válido")
    @EmailAvailable
    var email: String,

    @field:NotEmpty(message = "password deve ser informada")
    var password: String
)
