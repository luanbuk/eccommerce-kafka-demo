package br.com.ecommerce.users

import br.com.ecommerce.consumers.GenericConsumer
import br.com.ecommerce.kafka.Topic
import br.com.ecommerce.orders.Order
import br.com.ecommerce.orders.OrderDeserializer
import mu.KLogging
import org.apache.kafka.clients.consumer.ConsumerRecord
import java.util.*

class UsersService : GenericConsumer<Order>(
        groupId = UsersService::class.java.name,
        topics = listOf(Topic.ECOMMERCE_ORDER_APPROVED),
        deserializer = OrderDeserializer::class.java
) {

    private val usersRepository: UsersRepository = UsersRepositoryImpl()

    init {
        usersRepository.createTable()
    }

    override fun processMessage(message: ConsumerRecord<String, Order>) {
        if (message.value().emailExists()) {
            logger.info { "E-mail [${message.value().email}] já presente em base de dados" }
        } else{
           message.value().insertNewUser()
        }
    }

    private fun Order.emailExists() = this@UsersService.usersRepository.exists(this.email)

    private fun Order.insertNewUser() = this.also {
        val inserted = usersRepository.insert(UUID.randomUUID().toString(), this.email)

        if(inserted){
            logger.info { "E-mail [${this.email}] inserido em base de dados" }
        }else{
            logger.info { "E-mail [${this.email}] falha ao inserir em base de dados" }
        }
    }

    companion object : KLogging(){
        @JvmStatic
        fun main(args: Array<String>) {
            UsersService().start()
        }
    }
}