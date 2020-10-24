package br.com.ecommerce.orders

import br.com.ecommerce.arch.NoArgs

@NoArgs
data class OrderDTO constructor(
        val email: String,
        val value: Double
)