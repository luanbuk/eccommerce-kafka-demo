package br.com.ecommerce.consumers

import br.com.ecommerce.arch.NoArgs
import br.com.ecommerce.kafka.Topic
import br.com.ecommerce.orders.Order
import br.com.ecommerce.orders.OrderDeserializer
import br.com.ecommerce.orders.dispatchers.ApprovedOrderDispatcher
import br.com.ecommerce.orders.dispatchers.RejectedOrderDispatcher
import mu.KLogging
import org.apache.kafka.clients.consumer.ConsumerRecord

@NoArgs
class FraudDetectorService : GenericConsumer<Order>(
        groupId = FraudDetectorService::class.java.name,
        topics = listOf(Topic.ECOMMERCE_NEW_ORDER),
        deserializer = OrderDeserializer::class.java
) {
    private val approvedDispatcher = ApprovedOrderDispatcher()

    private val rejectedDispatcher = RejectedOrderDispatcher()

    override fun processMessage(message: ConsumerRecord<String, Order>) {
        if(message.value().fraudulent()){
            logger.info { "Pedido faudulento: ${message.value()}" }
            approvedDispatcher.create(message.value().email, message.value())

        }else{
            logger.info { "Pedido aprovado: ${message.value()}" }

            rejectedDispatcher.create(message.value().email, message.value())
        }
    }

    companion object : KLogging(){
        @JvmStatic
        fun main(args: Array<String>) {
            FraudDetectorService().start()
        }
    }
}