package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.extension.toBook
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("book")
class BookController(
    val bookService: BookService,
    val customerService: CustomerService
) {
    @GetMapping
    fun getAllBooks(@PageableDefault(page = 0, size = 10) pageable: Pageable): ResponseEntity<Page<BookResponse>> {
        val response = bookService.getAllBooks(pageable).map { it.toResponse() }
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/active")
    fun getBooksByStatusActive(@PageableDefault(page = 0, size = 10) pageable: Pageable) : ResponseEntity<Page<BookResponse>> {
        val response = bookService.findByStatusActive(pageable).map { it.toResponse() }
        return ResponseEntity.ok().body(response)
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") id: Int) : ResponseEntity<BookResponse>{
        val response = bookService.findById(id).toResponse()
        return ResponseEntity.ok().body(response)
    }

    @PostMapping("/create")
    fun create(@RequestBody request: PostBookRequest): ResponseEntity<Any> {
        val id = customerService.getCustomer(request.customerId)
        bookService.create(request.toBook(id))
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("/{id}")
    fun changeStatusToCancelled(@PathVariable("id") id: Int): ResponseEntity<Any> {
        bookService.delete(id)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody request: PutBookRequest): ResponseEntity<Any>{
        val bookSaved = bookService.findById(id)
        bookService.update(request.toBook(bookSaved))
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}

