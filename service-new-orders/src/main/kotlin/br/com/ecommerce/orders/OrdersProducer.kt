package br.com.ecommerce.orders

import br.com.ecommerce.kafka.Topics.ECOMMERCE_NEW_ORDER
import br.com.ecommerce.producers.GenericProducer
import java.math.BigDecimal
import java.util.*

class OrdersProducer : GenericProducer<Order>(ECOMMERCE_NEW_ORDER){
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

            (1..1050).forEach {
                OrdersProducer().create(value = Order(
                        userId = UUID.randomUUID().toString(),
                        orderId = UUID.randomUUID().toString(),
                        value = BigDecimal.valueOf((Math.random() * 5_000) + it)
                ))
            }
        }
    }
}