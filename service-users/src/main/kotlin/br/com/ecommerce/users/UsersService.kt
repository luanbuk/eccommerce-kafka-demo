package br.com.ecommerce.users

import br.com.ecommerce.consumers.GenericConsumer
import br.com.ecommerce.kafka.Topics
import br.com.ecommerce.orders.Order
import br.com.ecommerce.orders.OrderDeserializer
import mu.KLogging
import org.apache.kafka.clients.consumer.ConsumerRecord

class UsersService : GenericConsumer<Order>(
        groupId = UsersService::class.java.name,
        topics = listOf(Topics.ECOMMERCE_ORDER_APPROVED),
        deserializer = OrderDeserializer::class.java
) {

    private val usersRepository: UsersRepository = UsersRepositoryImpl()

    init {
        usersRepository.createTable()
    }

    override fun processMessage(message: ConsumerRecord<String, Order>) {
        if (this.usersRepository.exists(message.value().userEmail)) {
            logger.info { "E-mail [${message.value().userEmail}] já presente em base de dados" }
        } else{
            val inserted = message.value().let { order ->
                usersRepository.insert(order.userId, order.userEmail)
            }

            if(inserted){
                logger.info { "E-mail [${message.value().userEmail}] inserido em base de dados" }
            }else{
                logger.info { "E-mail [${message.value().userEmail}] falha ao inserir em base de dados" }
            }
        }
    }

    companion object : KLogging(){
        @JvmStatic
        fun main(args: Array<String>) {
            UsersService().start()
        }
    }
}