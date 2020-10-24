package br.com.ecommerce.emails

import br.com.ecommerce.kafka.Topics
import br.com.ecommerce.producers.GenericProducer

class EmailProducer : GenericProducer<Email>(Topics.ECOMMERCE_SEND_EMAIL)