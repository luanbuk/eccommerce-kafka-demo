package br.com.ecommerce.users

class UsersRepositoryImpl : GenericRepository(), UsersRepository {

    override val connectionUrl = "jdbc:sqlite:target/users_database.db"

    override fun createTable() {
        try {
            this.getConnection().use {
                it.createStatement().execute("create table if not exists Users (" +
                        "uuid varchar(200) primary key," +
                        "email varchar(200))")
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    override fun insert(userId: String, userEmail: String): Boolean {
        try {
            this.getConnection().use {
                return it.prepareStatement("insert into Users (uuid, email) values (?, ?)")
                        .let { insert ->
                            insert.setString(1, userId)
                            insert.setString(2, userEmail)
                            insert.executeUpdate() != 0
                        }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return false
    }

    override fun exists(userEmail: String): Boolean {
        try {
            this.getConnection().use {
                return it.prepareStatement("select uuid from Users where email = ? limit 1")
                        .let { select ->
                            select.setString(1, userEmail)
                            select.executeQuery().next()
                        }
            }
        } catch (e: Throwable) {
            e.printStackTrace()
        }
        return false
    }
}