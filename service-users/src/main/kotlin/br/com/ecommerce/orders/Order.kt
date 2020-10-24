package br.com.ecommerce.orders

import org.apache.commons.lang3.builder.ToStringBuilder
import java.math.BigDecimal

class Order(
        val orderId: String,
        val email: String,
        val value: BigDecimal
){
    override fun toString() = ToStringBuilder.reflectionToString(this)
}