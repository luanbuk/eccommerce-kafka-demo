package br.com.ecommerce.consumers

import br.com.ecommerce.emails.Email
import br.com.ecommerce.emails.EmailDeserializer
import br.com.ecommerce.kafka.Topic
import mu.KLogging
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.kafka.clients.consumer.ConsumerRecord

class EmailService : GenericConsumer<Email>(
        groupId = EmailService::class.java.name,
        topics = listOf(Topic.ECOMMERCE_SEND_EMAIL),
        deserializer = EmailDeserializer::class.java
) {
    override fun processMessage(message: ConsumerRecord<String, Email>) {
        logger.info { "Consumindo email: ${ToStringBuilder.reflectionToString(message.value())}" }
    }

    companion object : KLogging(){
        @JvmStatic
        fun main(args: Array<String>) {
            EmailService().start()
        }
    }
}