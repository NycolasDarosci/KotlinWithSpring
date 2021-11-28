package com.mercadolivro.service

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.enums.BookStatus
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
    fun getAllBooks(): List<Book> =
        bookRepository.findAll().toList()

    fun create(book: Book): Book =
        bookRepository.save(book)

    fun findByStatusActive(): List<Book> =
        bookRepository.findByStatus(BookStatus.ATIVO)

    fun findByStatusCancelled(): List<Book> =
        bookRepository.findByStatus(BookStatus.CANCELADO)

    fun findById(id: Int): Book = bookRepository.findById(id).orElseThrow()

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        create(book)
    }

    fun update(book: Book) =
        bookRepository.save(book)


}