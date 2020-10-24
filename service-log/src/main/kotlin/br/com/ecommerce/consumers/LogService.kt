package br.com.ecommerce.consumers

import br.com.ecommerce.kafka.Topic
import mu.KLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.common.serialization.StringDeserializer

class LogService : GenericConsumer<String>(
        groupId = LogService::class.java.name,
        topics = Topic.values().toList(),
        deserializer = StringDeserializer::class.java
) {
    override fun processMessage(message: ConsumerRecord<String, String>) {
        logger.info { "${message.topic()} LOG-Consumindo mensagens: ${message.value()}" }
    }

    companion object : KLogging() {
        @JvmStatic
        fun main(args: Array<String>) {
            LogService().start()
        }
    }
}