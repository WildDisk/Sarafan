package ru.wilddisk.plugins

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.serialization.Serializable
import ru.wilddisk.api.apiUser

fun Application.configureRouting() {
    var message = "Hello World!"
    routing {
        get("/") {
            call.respondText(message)
        }
        post("send") {
            message = call.receive<Message>().text
            call.respondRedirect("/")
        }
        route("api") {
            apiUser()
        }
    }
}

@Serializable
data class Message(val text: String)