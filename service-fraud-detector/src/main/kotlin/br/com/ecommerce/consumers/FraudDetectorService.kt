package br.com.ecommerce.consumers

import br.com.ecommerce.arch.NoArgs
import br.com.ecommerce.kafka.Topics
import br.com.ecommerce.orders.Order
import br.com.ecommerce.orders.OrderDeserializer
import br.com.ecommerce.orders.dispatchers.ApprovedOrderDispatcher
import br.com.ecommerce.orders.dispatchers.RejectedOrderDispatcher
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.kafka.clients.consumer.ConsumerRecord
import java.math.BigDecimal

@NoArgs
class FraudDetectorService : GenericConsumer<Order>(
        groupId = FraudDetectorService::class.java.name,
        topics = listOf(Topics.ECOMMERCE_NEW_ORDER),
        deserializer = OrderDeserializer::class.java
) {
    private val approvedDispatcher = ApprovedOrderDispatcher()

    private val rejectedDispatcher = RejectedOrderDispatcher()

    override fun processMessage(message: ConsumerRecord<String, Order>) {
        if(message.value().fraudulent()){
            println( "Pedido faudulento: ${message.value()}" )
            approvedDispatcher.create(message.value().userId, message.value())

        }else{
            println( "Pedido aprovado: ${message.value()}" )

            rejectedDispatcher.create(message.value().userId, message.value())
        }
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            FraudDetectorService().start()
        }
    }
}