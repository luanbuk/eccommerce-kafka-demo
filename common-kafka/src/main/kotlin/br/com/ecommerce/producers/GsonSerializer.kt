package br.com.ecommerce.producers

import com.google.gson.GsonBuilder
import org.apache.kafka.common.serialization.Serializer

class GsonSerializer : Serializer<Any> {

    private val gson = GsonBuilder().create()

    override fun serialize(p0: String?, obj: Any?) = gson.toJson(obj).toByteArray()
}