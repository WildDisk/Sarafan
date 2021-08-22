package ru.wilddisk

import io.ktor.application.*
import ru.wilddisk.data.db.ConnectionDatabase
import ru.wilddisk.plugins.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    ConnectionDatabase
    configureJwt()
    configureRouting()
    configureHTTP()
    configureSerialization()
    configureSockets()
    configureMonitoring()
}
