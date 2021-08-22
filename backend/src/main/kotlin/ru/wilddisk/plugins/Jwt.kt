package ru.wilddisk.plugins

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.auth.jwt.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import ru.wilddisk.auth.LoginUser
import ru.wilddisk.data.model.User
import java.util.*

fun Application.configureJwt() {
    authentication {
        val secret = environment.config.property("jwt.secret").getString()
        val issuer = environment.config.property("jwt.issuer").getString()
        val audience = environment.config.property("jwt.audience").getString()
        jwt {
            realm = environment.config.property("jwt.realm").getString()
            verifier(
                JWT
                    .require(Algorithm.HMAC256(secret))
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build()
            )
            validate { jwtCredential ->
                val username = jwtCredential.payload.getClaim("username").asString()
                val password = jwtCredential.payload.getClaim("password").asString()
//                val email = jwtCredential.payload.getClaim("email").asString()
//                if ((username.isNotEmpty() || email.isNotEmpty()) && password.isNotEmpty())
                if (username != null && password != null)
                    LoginUser(
                        User(username = username, password = password, /*email = email*/)
                    ).login()
                else null
            }
        }
        routing {
            post("login") {
                val user = call.receive<User>()
                val token = JWT.create()
                    .withSubject("Authentication")
                    .withIssuer(issuer)
                    .withClaim("username", user.username)
                    .withClaim("password", user.password)
//                    .withClaim("email", user.email)
                    .withExpiresAt(Date(System.currentTimeMillis() + 60_000))
                    .sign(Algorithm.HMAC256(secret))
                call.respond(hashMapOf("token" to token))
            }
        }
    }
}