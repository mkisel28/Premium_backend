package news.slivy

import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.netty.*
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
import news.slivy.feature.login.configureLoginRouting
import news.slivy.feature.profile.configureProfileRouting
import news.slivy.feature.register.configureRegisterRouting
import news.slivy.plugins.*
import org.jetbrains.exposed.sql.Database

fun main() {

//    Database.connect(
//        "jdbc:postgresql://localhost:5432/slivy_test",
//        driver = "org.postgresql.Driver",
//        user = "slivy_test",
//        password = "test"
//    )


    Database.connect(
        "jdbc:postgresql://localhost:5432/premium",
        driver = "org.postgresql.Driver",
        user = "postgres",
        password = "Maksim2001"
    )



    embeddedServer(CIO, port = 8080, host = "0.0.0.0") {
        configureHTTP()
        configureRouting()
        configureSerialization()
        configureLoginRouting()
        configureRegisterRouting()
        configureProfileRouting()
    }.start(wait = true)
}
