package br.com.ecommerce.users

interface UsersRepository {

    fun createTable()

    fun insert(userId: String, userEmail: String): Boolean

    fun exists(userEmail: String): Boolean

}
