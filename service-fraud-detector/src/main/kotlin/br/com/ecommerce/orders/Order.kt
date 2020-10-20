package br.com.ecommerce.orders

import java.math.BigDecimal

data class Order(
        val userId: String,
        val orderId: String,
        val value: BigDecimal
)