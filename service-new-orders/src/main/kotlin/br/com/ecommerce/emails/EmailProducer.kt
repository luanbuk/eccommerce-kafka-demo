package br.com.ecommerce.emails

import br.com.ecommerce.kafka.Topic
import br.com.ecommerce.producers.GenericProducer

class EmailProducer : GenericProducer<Email>(Topic.ECOMMERCE_SEND_EMAIL)