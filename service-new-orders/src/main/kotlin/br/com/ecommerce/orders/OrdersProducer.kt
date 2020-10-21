package br.com.ecommerce.orders

import br.com.ecommerce.kafka.Topics.ECOMMERCE_NEW_ORDER
import br.com.ecommerce.producers.GenericProducer
import java.math.BigDecimal
import java.util.*

class OrdersProducer : GenericProducer<Order>(ECOMMERCE_NEW_ORDER){
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

            (1..10).forEach {
                val id = UUID.randomUUID().toString()
                OrdersProducer().create(value = Order(
                        userId = id,
                        userEmail = "$id@gmail.com",
                        orderId = UUID.randomUUID().toString(),
                        value = BigDecimal.valueOf((Math.random() * 5_000) + it)
                ))
            }
        }
    }
}