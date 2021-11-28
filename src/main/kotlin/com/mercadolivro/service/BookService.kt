package com.mercadolivro.service

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.extension.toBook
import com.mercadolivro.extension.toCustomer
import com.mercadolivro.model.Book
import com.mercadolivro.repository.BookRepository
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository,
) {

    fun getAllBooks(): List<Book> {
        return bookRepository.findAll().toList()
    }

    fun create(book: Book): Book {

        return bookRepository.save(book)
    }

}