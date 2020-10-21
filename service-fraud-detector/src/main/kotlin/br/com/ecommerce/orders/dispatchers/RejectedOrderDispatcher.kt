package br.com.ecommerce.orders.dispatchers

import br.com.ecommerce.kafka.Topics.ECOMMERCE_ORDER_REJECTED
import br.com.ecommerce.orders.Order
import br.com.ecommerce.producers.GenericProducer

class RejectedOrderDispatcher : GenericProducer<Order>(ECOMMERCE_ORDER_REJECTED)