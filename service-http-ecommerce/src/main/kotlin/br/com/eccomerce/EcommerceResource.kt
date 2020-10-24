package br.com.eccomerce

import br.com.ecommerce.emails.Email
import br.com.ecommerce.emails.EmailProducer
import br.com.ecommerce.orders.Order
import br.com.ecommerce.orders.OrderDTO
import br.com.ecommerce.orders.OrdersProducer
import mu.KLogging
import java.math.BigDecimal
import javax.enterprise.context.RequestScoped
import javax.ws.rs.*
import javax.ws.rs.core.MediaType.APPLICATION_JSON
import javax.ws.rs.core.Response

@Path("orders")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@RequestScoped
class EcommerceResource {

    private val ordersProducer = OrdersProducer()

    private val emailproducer = EmailProducer()

    @POST
    fun create(dto: OrderDTO): Response{
        dto.toOrder().publish()
        dto.toEmail().publish()
        return Response.ok("A new order was created!").build()
    }

    private fun OrderDTO.toOrder()  = Order(
            email = this.email,
            value = BigDecimal.valueOf(this.value)
    )

    private fun Order.publish() = this@EcommerceResource.ordersProducer.create(
            key = this.email,
            value = this
    )

    private fun OrderDTO.toEmail() = Email(
            recipient = this.email,
            subject = "New Order",
            body = "Thank you for buying with us!"
    )

    private fun Email.publish() = this@EcommerceResource.emailproducer.create(
            key = this.recipient,
            value = this
    )

    companion object : KLogging()
}