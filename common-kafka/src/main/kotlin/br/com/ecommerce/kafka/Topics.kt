package br.com.ecommerce.kafka

enum class Topics {
    ECOMMERCE_NEW_ORDER,
    ECOMMERCE_SEND_EMAIL,
    ECOMMERCE_ORDER_REJECTED,
    ECOMMERCE_ORDER_APPROVED;

    val identifier: String = this.name
}