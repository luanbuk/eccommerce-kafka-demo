package br.com.ecommerce.orders

import java.math.BigDecimal

data class Order(
        val orderId: String,
        val email: String,
        val value: BigDecimal
)