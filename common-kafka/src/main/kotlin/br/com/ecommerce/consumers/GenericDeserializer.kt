package br.com.ecommerce.consumers

import com.google.gson.GsonBuilder
import org.apache.kafka.common.serialization.Deserializer
import java.io.ByteArrayInputStream
import java.io.InputStreamReader

open class GenericDeserializer<T>(
        val clazz: Class<T>
) : Deserializer<Any> {

    private val gson = GsonBuilder().create()

    override fun deserialize(p0: String?, value: ByteArray?) = gson.fromJson(InputStreamReader(ByteArrayInputStream(value)), clazz)
}