package br.com.ecommerce.orders

import org.apache.commons.lang3.builder.ToStringBuilder
import java.math.BigDecimal

class Order(
        val userId: String,
        val orderId: String,
        val userEmail: String,
        val value: BigDecimal
){

    fun fraudulent() = BigDecimal("3000") >= this.value

    override fun toString() = ToStringBuilder.reflectionToString(this)
}