package ru.wilddisk.data.model

import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.orWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import ru.wilddisk.data.entity.Roles
import ru.wilddisk.data.entity.Users

class UserRoles(private val user: User) {
    fun roles(): Set<Role> {
        val roleSet = mutableSetOf<Role>()
        transaction {
            (Users leftJoin Roles)
                .select { Users.id eq Roles.userId }
                .andWhere { Users.id eq user.id }
                .orWhere { Users.username eq user.username }
                .orWhere { Users.email eq user.email }
                .toList()
        }.forEach {
            roleSet.add(Role.valueOf(it[Roles.role]))
        }
        return roleSet
    }
}