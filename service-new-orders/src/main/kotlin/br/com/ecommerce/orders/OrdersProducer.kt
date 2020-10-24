package br.com.ecommerce.orders

import br.com.ecommerce.emails.Email
import br.com.ecommerce.emails.EmailProducer
import br.com.ecommerce.kafka.Topic.ECOMMERCE_NEW_ORDER
import br.com.ecommerce.producers.GenericProducer
import java.math.BigDecimal
import java.util.*

class OrdersProducer : GenericProducer<Order>(ECOMMERCE_NEW_ORDER){
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

            val email = "teste@gmail.com"

            (1..5).forEach {
                OrdersProducer().create(
                        key = email,
                        value = Order(
                        email = email,
                        orderId = UUID.randomUUID().toString(),
                        value = BigDecimal.valueOf((Math.random() * 5_000) + it)
                ))

                EmailProducer().create(value = Email(
                        recipient = email,
                        subject = "New Order",
                        body = "Thank you for buying with us!"
                ))
            }
        }
    }
}