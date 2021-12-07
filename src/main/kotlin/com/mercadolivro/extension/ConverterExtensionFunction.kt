package com.mercadolivro.extension

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer

/*
    you can write new functions for a class from a
    third-party library that you can't modify.
    Such functions can be called in the usual way, as if they were methods of the original class.
    This mechanism is called an extension function.
    There are also extension properties that let you define new properties for existing classes.

*/
fun PostCustomerRequest.toCustomer(): Customer {
    return Customer(
        name = this.name,
        email = this.email,
        status = CustomerStatus.ATIVO
    )
}

fun PutCustomerRequest.toCustomer(customerSaved: Customer): Customer {
    return Customer(
        id = customerSaved.id ,
        name = this.name,
        email = this.email,
        status = this.status ?: customerSaved.status
    )
}

fun PostBookRequest.toBook(customer: Customer): Book {
    return Book(
        name = this.name,
        price = this.price,
        customer = customer,
        status = BookStatus.ATIVO,
    )
}

fun PutBookRequest.toBook(previousValue: Book): Book {
    return Book(
        /*
            elvis operator ?:
            se o valor a esquerda for nula, retorna o valor a direita e vice-versa
        */
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        customer = previousValue.customer,
        status = previousValue.status,
    )
}