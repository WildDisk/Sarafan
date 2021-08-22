package ru.wilddisk.data.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.transactions.transaction
import ru.wilddisk.data.entity.Roles
import ru.wilddisk.data.entity.Users
import ru.wilddisk.data.model.Role
import ru.wilddisk.data.model.User

/**
 *
 * @author wilddisk on 04.08.2021
 */
object ConnectionDatabase {
    init {
        Database.connect(HikariDataSource(HikariConfig("/hikari.properties").apply { schema = "public" }))
        val tables = mutableListOf<Table>()
        val user = User(role = setOf(Role.ADMIN))
        var userTableMustBeCreated = false
        try {
            user.findAll(0, 0)
        } catch (e: Exception) {
            tables.apply {
                add(Users)
                add(Roles)
                userTableMustBeCreated = true
            }
        }
        if (tables.isNotEmpty()) {
            transaction { SchemaUtils.create(*tables.toTypedArray()) }
            if (userTableMustBeCreated)
                User(
                    id = -1,
                    username = "admin",
                    password = "admin",
                    firstName = "",
                    lastName = "",
                    email = "admin@example.com",
                    role = setOf(Role.ADMIN, Role.USER)
                ).save()
        }
    }
}