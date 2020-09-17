package br.com.ecommerce.consumers

import br.com.ecommerce.emails.Email
import br.com.ecommerce.emails.EmailDeserializer
import br.com.ecommerce.kafka.Topics.ECOMMERCE_SEND_EMAIL
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.kafka.clients.consumer.ConsumerRecord

class EmailService : GenericConsumer<Email>(
        groupId = EmailService::class.java.name,
        topics = listOf(ECOMMERCE_SEND_EMAIL),
        deserializer = EmailDeserializer::class.java
) {
    override fun processMessage(message: ConsumerRecord<String, Email>) {
        println( "Consumindo email: ${ToStringBuilder.reflectionToString(message.value())}" )
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            EmailService().start()
        }
    }
}