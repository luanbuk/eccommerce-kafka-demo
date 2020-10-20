package br.com.ecommerce.consumers

import br.com.ecommerce.arch.NoArgs
import br.com.ecommerce.kafka.Topics
import br.com.ecommerce.orders.Order
import br.com.ecommerce.orders.OrderDeserializer
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.kafka.clients.consumer.ConsumerRecord

@NoArgs
class FraudDetectorService : br.com.ecommerce.consumers.GenericConsumer<Order>(
        groupId = FraudDetectorService::class.java.name,
        topics = listOf(Topics.ECOMMERCE_NEW_ORDER),
        deserializer = OrderDeserializer::class.java
) {
    override fun processMessage(message: ConsumerRecord<String, Order>) {
        println( "Consumindo order: ${ToStringBuilder.reflectionToString(message.value())}" )
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            FraudDetectorService().start()
        }
    }
}