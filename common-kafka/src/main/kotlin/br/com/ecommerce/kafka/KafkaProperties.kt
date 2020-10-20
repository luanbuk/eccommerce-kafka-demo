package br.com.ecommerce.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*

object KafkaProperties {

    val producers = Properties().also {
        it.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        it.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, br.com.ecommerce.producers.GsonSerializer::class.java.name)
        it.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, br.com.ecommerce.producers.GsonSerializer::class.java.name)
    }

    val consumers = Properties().also {
        it.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092")
        it.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        it.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        it.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1") //consome somente 1 mensagem por vez evitando problemas com rebalance
    }

}