package br.com.ecommerce.kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import java.util.*

object KafkaProperties {

    val producers = Properties().also {
        it.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9090")
        it.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, br.com.ecommerce.producers.GsonSerializer::class.java.name)
        it.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, br.com.ecommerce.producers.GsonSerializer::class.java.name)
        it.setProperty(ProducerConfig.ACKS_CONFIG, "all") //This means the leader will wait for the full set of in-sync replicas to acknowledge the record. This guarantees that the record will not be lost as long as at least one in-sync replica remains alive (see ProducerConfig.ACKS_DOC)
    }

    val consumers = Properties().also {
        it.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9090")
        it.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        it.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer::class.java.name)
        it.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1") //consome somente 1 mensagem por vez evitando problemas com rebalance
    }

}