package br.com.ecommerce.users

import java.sql.DriverManager.getConnection

abstract class GenericRepository {

    protected abstract val connectionUrl: String

    fun getConnection() = getConnection(connectionUrl)
}