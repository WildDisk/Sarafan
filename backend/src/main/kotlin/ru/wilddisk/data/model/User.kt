package ru.wilddisk.data.model

import io.ktor.auth.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.wilddisk.data.Crud
import ru.wilddisk.data.entity.Roles
import ru.wilddisk.data.entity.Users

/**
 *
 * @author wilddisk on 04.08.2021
 */
@Serializable
data class User(
    val id: Long = -1,
    val username: String = "",
    val password: String = "",
    @SerialName("first_name")
    val firstName: String = "",
    @SerialName("last_name")
    val lastName: String = "",
    val email: String = "",
    val role: Set<Role> = setOf(Role.USER)
) : Crud<User>, Principal {
    override fun find(): User {
        var desiredUser = User()
        when {
            id > 0 -> transaction { Users.select { Users.id eq id }.toList() }
            username.isNotEmpty() -> transaction { Users.select { Users.username eq username }.toList() }
            email.isNotEmpty() -> transaction { Users.select { Users.email eq email }.toList() }
            else -> listOf()
        }.forEach {
            desiredUser = User(
                id = it[Users.id],
                username = it[Users.username],
                password = it[Users.password],
                firstName = it[Users.firstName],
                lastName = it[Users.lastName],
                email = it[Users.email],
                role = UserRoles(this).roles()
            )
        }
        return desiredUser
    }

    override fun findAll(limit: Int, offset: Int): List<User> {
        val users = mutableListOf<User>()
        transaction {
            Users
                .selectAll()
                .orderBy(Users.id)
                .limit(n = limit, offset = offset)
                .forEach {
                    users.add(
                        User(
                            id = it[Users.id],
                            username = it[Users.username],
                            password = it[Users.password],
                            firstName = it[Users.firstName],
                            lastName = it[Users.lastName],
                            email = it[Users.email],
                            role = UserRoles(
                                User(
                                    id = it[Users.id],
                                    username = it[Users.username],
                                    email = it[Users.email]
                                )
                            ).roles()
                        )
                    )
                }
        }
        return users
    }

    override fun save() {
        transaction {
            Users.insert {
                it[username] = this@User.username
                it[password] = this@User.password
                it[firstName] = this@User.firstName
                it[lastName] = this@User.lastName
                it[email] = this@User.email
            }
            this@User.role.forEach { roleItem ->
                Roles.insert {
                    it[userId] = Users.select { Users.username eq this@User.username }.single()[Users.id]
                    it[role] = roleItem.name
                }
            }
        }
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun delete() {
        TODO("Not yet implemented")
    }
}
