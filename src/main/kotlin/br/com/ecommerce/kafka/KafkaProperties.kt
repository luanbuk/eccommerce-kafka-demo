package br.com.ecommerce.kafka

import br.com.ecommerce.producers.GsonSerializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerConfig.*
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.clients.producer.ProducerConfig.*
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.common.serialization.StringSerializer
import java.util.*

object KafkaProperties {

    val producers = Properties().also {
        it.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        it.setProperty(KEY_SERIALIZER_CLASS_CONFIG, GsonSerializer::class.java.name)
        it.setProperty(VALUE_SERIALIZER_CLASS_CONFIG, GsonSerializer::class.java.name)
    }

    val consumers = Properties().also {
        it.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        it.setProperty(KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        it.setProperty(VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        it.setProperty(MAX_POLL_RECORDS_CONFIG, "1") //consome somente 1 mensagem por vez evitando problemas com rebalance
    }

}