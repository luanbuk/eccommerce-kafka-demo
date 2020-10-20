package br.com.ecommerce.kafka

enum class Topics(val identifier: String) {
    ECOMMERCE_NEW_ORDER("ECOMMERCE_NEW_ORDER"),
    ECOMMERCE_SEND_EMAIL("ECOMMERCE_SEND_EMAIL")
}