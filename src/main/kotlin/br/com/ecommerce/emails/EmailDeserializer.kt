package br.com.ecommerce.emails

import br.com.ecommerce.consumers.GenericDeserializer

class EmailDeserializer : GenericDeserializer<Email>(Email::class.java)