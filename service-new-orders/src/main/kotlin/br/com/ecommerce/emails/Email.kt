package br.com.ecommerce.emails

data class Email(
        val recipient: String,
        val subject: String,
        val body: String
)