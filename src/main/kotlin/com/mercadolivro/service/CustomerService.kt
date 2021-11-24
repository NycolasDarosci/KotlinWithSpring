package com.mercadolivro.service

import com.mercadolivro.model.Customer
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    //private val customers = mutableListOf<Customer>()

    fun getAllCustomers(name: String?): List<Customer>{

        name?.let {
            return customerRepository.findByNameContaining(it)
        }

        return customerRepository.findAll().toList()

        /*
            Está verificando(name?) se o nome não vier nulo,
            faz a filtragem do parametro.

            if(name != null){
                ...
            }
        */
        /*
        name?.let {
            val param = customers.filter { it.name.contains(name, true) }
            return param
        }
        return customers
         */
    }

    fun getCustomer( id: Int): Customer {

        return customerRepository.findById(id).orElseThrow()

        /*
        val id = customers.filter { it.id == id }.first()
        return id
         */
    }

    fun update( customer: Customer) {

        // com exclamação no '!customerRepository' é negação. Se não existir id, joga exception
        if(!customerRepository.existsById(customer.id!!)){
            throw Exception()
        }

        customerRepository.save(customer)
        /*
        val id = customers.filter { it.id == customer.id }.first().let {
            it.name = customer.name
            it.email = customer.email
        }

        return id
         */
    }

    fun create( customer: Customer) {

        customerRepository.save(customer)

        val id = if (customers.isEmpty()) 1 else { customers.last().id.toInt() + 1 }.toString()

        /*val id = if (customers.isEmpty()) 1 else {customers.last().id!!.toInt() + 1 }

        customer.id = id

        customers.add(Customer(id, customer.name, customer.email))

        */
    }

    fun delete( id: Int) {

        customerRepository.deleteById(id)
        //customers.removeIf { it.id == id }
    }
}