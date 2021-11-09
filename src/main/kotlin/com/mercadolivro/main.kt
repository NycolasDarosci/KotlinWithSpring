package com.mercadolivro

import com.mercadolivro.model.Customer

/*
    Método de testes apenas
*/
fun main(){
    val jack = Customer("1","Nycolas","nycolas@example.com")
    val another = jack.copy(id = "2")

    println(another)
}