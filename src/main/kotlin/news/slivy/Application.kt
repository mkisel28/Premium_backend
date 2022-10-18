package news.slivy

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.netty.*
import kotlinx.coroutines.DEBUG_PROPERTY_NAME
import kotlinx.coroutines.DEBUG_PROPERTY_VALUE_ON
import news.slivy.feature.login.configureLoginRouting
import news.slivy.feature.profile.configureProfileRouting
import news.slivy.feature.register.configureRegisterRouting
import news.slivy.plugins.*
import news.slivy.security.hashing.toMD5
import org.jetbrains.exposed.sql.Database

fun main() {

//    Database.connect(
//        "jdbc:postgresql://localhost:5432/slivy_test",
//        driver = "org.postgresql.Driver",
//        user = "slivy_test",
//        password = "test"
//    )

    val dbPass = System.getenv("DATABASE_PASSWORD")

    Database.connect(
        "jdbc:postgresql://45.144.30.46:5432/slivy_test",
        driver = "org.postgresql.Driver",
        user = "slivy_test",
        password = dbPass
    )


}


fun main(args: Array<String>): Unit =
    io.ktor.server.cio.EngineMain.main(args)


@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureHTTP()
    configureRouting()
    configureSerialization()
    configureLoginRouting()
    configureRegisterRouting()
    configureProfileRouting()

    println(toMD5().Create("fdfdfdd"))



    val dbPass = System.getenv("DATABASE_PASSWORD")
    Database.connect(
        "jdbc:postgresql://45.144.30.46:5432/slivy_test",
        driver = "org.postgresql.Driver",
        user = "slivy_test",
        password = dbPass
    )
}