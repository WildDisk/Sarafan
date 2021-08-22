package ru.wilddisk.auth

import ru.wilddisk.data.model.User

/**
 *
 * @author wilddisk on 04.08.2021
 */
class LoginUser(private val user: User) {
    fun login(): User = user.let {
        val authorizedUser: User = if (user.find().id > 0) user.find() else throw Exception("Something wrong!")
        authorizedUser
    }
}