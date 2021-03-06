package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository,
    val bookService: BookService
) {

    //private val customers = mutableListOf<Customer>()

    fun getAllCustomers(name: String?, pageable: Pageable): Page<Customer>{

        name?.let {
            return customerRepository.findByNameContaining(it, pageable)
        }

        return customerRepository.findAll(pageable)

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

        return customerRepository.findById(id)
            .orElseThrow {
                NotFoundException(
                    message = Errors.ML201.message.format(id),
                    errorCode = Errors.ML201.code)
            }

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

        /*val id = if (customers.isEmpty()) 1 else {customers.last().id!!.toInt() + 1 }

        customer.id = id

        customers.add(Customer(id, customer.name, customer.email))

        */
    }

    fun delete( id: Int) {
        val customer = getCustomer(id)
        bookService.deleteByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)

        //customers.removeIf { it.id == id }
    }

    fun emailAvailable(email: String): Boolean {
        /**
         *  verificar se existe o email
         *  se existir, retorna 'false', porque não esta disponível,
         *  se não existir, retorna 'true', porque está disponível
         */
        return !customerRepository.existsByEmail(email)
    }
}