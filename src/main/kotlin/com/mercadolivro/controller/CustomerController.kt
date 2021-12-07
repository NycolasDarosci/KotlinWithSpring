package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.extension.toCustomer
import com.mercadolivro.model.Customer
import com.mercadolivro.service.CustomerService
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

@RestController
@RequestMapping("customers")
class CustomerController(
    val customerService: CustomerService
)
{
    @GetMapping
    fun getAllCustomers(@RequestParam name: String?): ResponseEntity<List<Customer>>{

        val getAll = customerService.getAllCustomers(name)

        return ResponseEntity.ok().body(getAll)
    }

    @GetMapping("/{id}")
    fun getCustomer(@PathVariable("id") id: Int): ResponseEntity<Customer>{

        val idCustomer = customerService.getCustomer(id)

        return ResponseEntity.ok().body(idCustomer)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Int, @RequestBody customer: PutCustomerRequest): ResponseEntity<Customer>{
        val customerSaved = customerService.getCustomer(id)
        customerService.update(customer.toCustomer(customerSaved))

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @PostMapping("create")
    fun create(@RequestBody customer: PostCustomerRequest): ResponseEntity<Any>{

        customerService.create(customer.toCustomer())

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Int): ResponseEntity<Any>{

        customerService.delete(id)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }


}