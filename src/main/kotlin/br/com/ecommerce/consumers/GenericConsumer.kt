package br.com.ecommerce.consumers

import br.com.ecommerce.arch.NoArgs
import br.com.ecommerce.kafka.KafkaProperties
import br.com.ecommerce.kafka.Topics
import mu.KLogging
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.Deserializer
import java.time.Duration
import java.util.concurrent.Executors

@NoArgs
abstract class GenericConsumer<T>(
        private val groupId: String,
        private val topics: Collection<Topics>,
        private val deserializer: Class<out Deserializer<*>>
) {
    private val consumer = KafkaConsumer<String, T>(KafkaProperties.consumers.also {
        it.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        it.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer.name)
    }).also { c ->
        c.subscribe(topics.map { it.identifier })
    }

    fun start() {
        Executors.newCachedThreadPool().submit(this::monitor)
    }

    private fun monitor() {
        while (true) {
            var records = consumer.poll(Duration.ofMillis(100))

            if (records.isEmpty) {
                continue
            }

            records.forEach {
                this.processMessage(it)
            }
            //Thread.sleep(700)
        }
    }

    protected abstract fun processMessage(message: ConsumerRecord<String, T>)

    companion object : KLogging()
}