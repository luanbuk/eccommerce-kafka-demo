package br.com.ecommerce.consumers

import br.com.ecommerce.kafka.Topics
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer

class LogService : GenericConsumer<String>(
        groupId = LogService::class.java.name,
        topics = Topics.values().toList(),
        deserializer = StringDeserializer::class.java
) {
    override fun processMessage(message: ConsumerRecord<String, String>) {
        println( "LOG-Consumindo mensagens: ${ToStringBuilder.reflectionToString(message.value())}" )
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            LogService().start()
        }
    }
}