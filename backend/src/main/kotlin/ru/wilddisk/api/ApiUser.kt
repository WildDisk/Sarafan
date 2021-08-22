package ru.wilddisk.api

import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.wilddisk.data.model.User

fun Route.apiUser() {
    authenticate {
        get("user") {
            val user = call.principal<User>() ?: User()
            call.respond(user)
        }
    }
}