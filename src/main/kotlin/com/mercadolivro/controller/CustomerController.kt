package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.controller.response.CustomerResponse
import com.mercadolivro.extension.toCustomer
import com.mercadolivro.extension.toResponse
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
)
{
    @GetMapping
    fun getAllCustomers(
        @RequestParam name: String?,
        @PageableDefault(size = 10, page = 0) pageable: Pageable
    ): ResponseEntity<Page<CustomerResponse>> {
        val getAll = customerService.getAllCustomers(name, pageable).map { it.toResponse() }

        return ResponseEntity.ok().body(getAll)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable("id") id: Int): ResponseEntity<CustomerResponse> {
        val idCustomer = customerService.getCustomer(id).toResponse()

        return ResponseEntity.ok().body(idCustomer)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody @Valid customer: PutCustomerRequest): ResponseEntity<Any>{
        val customerSaved = customerService.getCustomer(id)
        customerService.update(customer.toCustomer(customerSaved))

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PostMapping("create")
    fun create(@RequestBody @Valid customer: PostCustomerRequest): ResponseEntity<Any>{

        customerService.create(customer.toCustomer())

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Any>{

        customerService.delete(id)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }


}