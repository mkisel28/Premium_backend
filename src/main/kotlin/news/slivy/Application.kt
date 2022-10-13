package news.slivy

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import news.slivy.feature.login.configureLoginRouting
import news.slivy.feature.register.configureRegisterRouting
import news.slivy.plugins.*
import org.jetbrains.exposed.sql.Database
import java.sql.Connection
import java.sql.DriverManager

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
