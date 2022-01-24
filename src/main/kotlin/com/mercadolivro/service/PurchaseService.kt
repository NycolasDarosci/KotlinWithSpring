package com.mercadolivro.service

import com.mercadolivro.event.PurchaseEvent
import com.mercadolivro.model.Purchase
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
    private val repository: PurchaseRepository,
    private val publicEventPublisher: ApplicationEventPublisher
) {

    fun create(purchase: Purchase) {
        repository.save(purchase)

        publicEventPublisher.publishEvent(PurchaseEvent(this, purchase))
    }

}
