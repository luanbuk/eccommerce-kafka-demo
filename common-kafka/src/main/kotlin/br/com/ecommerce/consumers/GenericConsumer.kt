package br.com.ecommerce.consumers

import br.com.ecommerce.arch.NoArgs
import br.com.ecommerce.kafka.KafkaProperties.consumers
import br.com.ecommerce.kafka.Topic
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
        private val topics: Collection<Topic>,
        private val deserializer: Class<out Deserializer<*>>
) {
    private val consumer = KafkaConsumer<String, T>(consumers.also {
        it.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId)
        it.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer.name)
    }).also { c ->
        c.subscribe(topics.map { it.identifier })
    }

    fun start() {
        Executors.newCachedThreadPool().submit(this::monitor)
    }

    private fun monitor() {
        try {
            while (true) {
                var records = consumer.poll(Duration.ofMillis(100))

                if (records.isEmpty) {
                    continue
                }

                records.forEach {
                    this.processMessage(it)
                }
            }
        } catch (e: Throwable){
            e.printStackTrace()
        }
    }

    protected abstract fun processMessage(message: ConsumerRecord<String, T>)

    companion object : KLogging()
}