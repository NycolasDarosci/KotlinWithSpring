package com.mercadolivro.repository

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {

    fun findByStatus(status: BookStatus): List<Book>

}