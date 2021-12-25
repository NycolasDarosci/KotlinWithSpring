package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.Book
import com.mercadolivro.model.Customer
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun getAllBooks(pageable: Pageable): Page<Book> =
        bookRepository.findAll(pageable)

    fun create(book: Book): Book =
        bookRepository.save(book)

    fun findByStatusActive(pageable: Pageable): Page<Book> =
        bookRepository.findByStatus(BookStatus.ATIVO, pageable)

    fun findById(id: Int): Book =
        bookRepository.findById(id)
            .orElseThrow {
                NotFoundException(
                    message = Errors.ML101.message.format(id),
                    errorCode = Errors.ML101.code
                )
            }

    fun update(book: Book) =
        bookRepository.save(book)

    fun delete(id: Int) {
        val book = findById(id)
        book.status = BookStatus.CANCELADO
        create(book)
    }

    fun deleteByCustomer(customer: Customer) {
        val books = bookRepository.findByCustomer(customer)

        for(book in books){
            book.status = BookStatus.CANCELADO
        }
        bookRepository.saveAll(books)
    }

}