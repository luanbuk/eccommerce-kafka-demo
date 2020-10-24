package br.com.ecommerce.orders

import java.math.BigDecimal
import java.util.*

data class Order(
        val orderId: String = UUID.randomUUID().toString(),
        val email: String,
        val value: BigDecimal
)