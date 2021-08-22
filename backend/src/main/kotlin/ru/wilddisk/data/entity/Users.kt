package ru.wilddisk.data.entity

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = long("id").primaryKey().uniqueIndex().autoIncrement()
    val username = varchar("username", 255).uniqueIndex().default("")
    val password = varchar("password", 255).default("")
    val firstName = varchar("first_name", 255).default("")
    val lastName = varchar("last_name", 255).default("")
    val email = varchar("email", 255).uniqueIndex().default("")
}