package br.com.ecommerce.orders

import br.com.ecommerce.emails.Email
import br.com.ecommerce.emails.EmailProducer
import br.com.ecommerce.kafka.Topics.ECOMMERCE_NEW_ORDER
import br.com.ecommerce.producers.GenericProducer
import java.math.BigDecimal
import java.util.*

class OrdersProducer : GenericProducer<Order>(ECOMMERCE_NEW_ORDER){
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {

            (1..5).forEach {

                val id = UUID.randomUUID().toString()
                val email = "$id@gmail.com"
                OrdersProducer().create(value = Order(
                        userId = id,
                        userEmail = "$id@gmail.com",
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