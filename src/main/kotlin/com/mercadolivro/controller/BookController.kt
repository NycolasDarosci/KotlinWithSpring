package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.extension.toBook
import com.mercadolivro.model.Book
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {
    @GetMapping
    fun getAllBooks(): ResponseEntity<List<Book>>{
        val books = bookService.getAllBooks()
        return ResponseEntity.ok().body(books)
    }

    @PostMapping("create")
    fun create(@RequestBody request: PostBookRequest): ResponseEntity<Book> {
        val id = customerService.getCustomer(request.customerId)
        bookService.create(request.toBook(id))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }
}