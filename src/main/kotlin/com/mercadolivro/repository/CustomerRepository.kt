package com.mercadolivro.repository

import com.mercadolivro.model.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository: CrudRepository<Customer, Int> {

    fun findByNameContaining(name: String, pageable: Pageable): Page<Customer>

    fun findAll(pageable: Pageable): Page<Customer>
}