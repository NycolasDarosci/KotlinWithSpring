package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.extension.toBook
import com.mercadolivro.model.Book
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
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
        return ResponseEntity.ok().body(bookService.getAllBooks())
    }

    @GetMapping("/active")
    fun getBooksByStatusActive() : ResponseEntity<List<Book>> {
        return ResponseEntity.ok().body(bookService.findByStatusActive())
    }

    @GetMapping("/cancelled")
    fun getBooksByStatusCancelled() : ResponseEntity<List<Book>> {
        return ResponseEntity.ok().body(bookService.findByStatusCancelled())
    }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable("id") id: Int) : ResponseEntity<Book>{
        return ResponseEntity.ok().body(bookService.findById(id))
    }

    @PostMapping("/create")
    fun create(@RequestBody request: PostBookRequest): ResponseEntity<Book> {
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

