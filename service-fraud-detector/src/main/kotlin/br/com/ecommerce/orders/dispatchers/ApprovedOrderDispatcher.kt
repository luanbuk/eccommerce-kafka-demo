package br.com.ecommerce.orders.dispatchers

import br.com.ecommerce.kafka.Topic.ECOMMERCE_ORDER_APPROVED
import br.com.ecommerce.orders.Order
import br.com.ecommerce.producers.GenericProducer

class ApprovedOrderDispatcher : GenericProducer<Order>(ECOMMERCE_ORDER_APPROVED)