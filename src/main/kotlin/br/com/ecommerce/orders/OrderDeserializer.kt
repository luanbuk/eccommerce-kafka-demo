package br.com.ecommerce.orders

import br.com.ecommerce.consumers.GenericDeserializer

class OrderDeserializer : GenericDeserializer<Order>(Order::class.java)