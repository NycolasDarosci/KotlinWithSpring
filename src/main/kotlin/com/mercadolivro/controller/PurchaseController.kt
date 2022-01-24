package com.mercadolivro.controller

import com.mercadolivro.controller.mapper.PurchaseMapper
import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/purchase")
class PurchaseController(
  val service: PurchaseService,
  val mapper: PurchaseMapper
) {

    @PostMapping
    fun purchase(@RequestBody @Valid request: PostPurchaseRequest) {
        service.create(mapper.toModel(request))

        ResponseEntity.status(HttpStatus.CREATED)
    }

}