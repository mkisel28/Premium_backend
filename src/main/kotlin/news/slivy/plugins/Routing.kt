package news.slivy.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Int,
    val title: String)

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respond(
                listOf(
                    Article(id = 5, title = "Привет мир"),
                    Article(id = 5, title = "Привет мир")
                )
            )

        }
    }
}
