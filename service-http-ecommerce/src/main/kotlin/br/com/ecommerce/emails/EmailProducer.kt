package br.com.ecommerce.emails

class EmailProducer : br.com.ecommerce.producers.GenericProducer<Email>(br.com.ecommerce.kafka.Topic.ECOMMERCE_SEND_EMAIL)