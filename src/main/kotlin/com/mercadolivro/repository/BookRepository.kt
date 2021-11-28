package com.mercadolivro.repository

import com.mercadolivro.model.Book
import org.springframework.data.repository.CrudRepository

interface BookRepository : CrudRepository<Book, Int> {
}