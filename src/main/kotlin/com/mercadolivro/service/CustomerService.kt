package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.Customer
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class CustomerService {

    private val customers = mutableListOf<Customer>()

    fun getAllCustomers(name: String?): List<Customer>{
        /*
            Está verificando(name?) se o nome não vier nulo,
            faz a filtragem do parametro.

            if(name != null){
                ...
            }
        */
        name?.let {
            val param = customers.filter { it.name.contains(name, true) }
            return param
        }
        return customers
    }

    fun getCustomer( id: String): Customer {
        val id = customers.filter { it.id == id }.first()
        return id
    }

    fun update( id: String, @RequestBody customer: PutCustomerRequest) {

        val id = customers.filter { it.id == id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }

        return id
    }

    fun create( customer: PostCustomerRequest) {

        val id = if (customers.isEmpty()) 1 else { customers.last().id.toInt() + 1 }.toString()

        customers.add(Customer(id, customer.name, customer.email))
    }

    fun delete( id: String) {
        customers.removeIf { it.id == id }
    }
}