package com.mercadolivro.controller.request

import java.math.BigDecimal

/**
 * don't validate each field when it's nullable
 */
data class PutBookRequest(
    var name: String?,
    var price: BigDecimal?
)
