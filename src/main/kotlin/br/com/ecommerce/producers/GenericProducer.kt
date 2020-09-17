package br.com.ecommerce.producers

import br.com.ecommerce.kafka.KafkaProperties
import br.com.ecommerce.kafka.Topics
import mu.KLogging
import org.apache.commons.lang3.builder.ToStringBuilder
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.RecordMetadata
import java.lang.Exception
import java.util.*

abstract class GenericProducer<T> (
        val topic: Topics
) {

    protected val kafkaProducer = KafkaProducer<String, T>(KafkaProperties.producers)

    fun create(key: String = UUID.randomUUID().toString(), value: T){
        this.kafkaProducer.send(ProducerRecord<String, T>( this.topic.identifier, key, value), this::productionResponse).get()
    }

    private fun productionResponse(record: RecordMetadata?, exception: Exception?){
        exception?.printStackTrace() ?: record?.let { println ( "Envio de mensagem:: ${ToStringBuilder.reflectionToString(it)}" ) }
    }

    companion object: KLogging()
}