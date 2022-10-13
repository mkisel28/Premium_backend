package news.s

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import news.s.feature.login.configureLoginRouting
import news.s.feature.register.configureRegisterRouting
import news.s.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {

    Database.connect("jdbc:postgresql://localhost:5432/premium", driver = "org.postgresql.Driver", user = "postgres", password = "Maksim2001")




    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureHTTP()
        configureRouting()
        configureSerialization()
        configureLoginRouting()
        configureRegisterRouting()
    }.start(wait = true)
}
