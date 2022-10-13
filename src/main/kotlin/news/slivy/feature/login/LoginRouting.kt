package news.slivy.feature.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import news.slivy.cache.InMemoryCache
import news.slivy.cache.TokenCache
import news.slivy.plugins.Article
import java.util.*

fun Application.configureLoginRouting() {

    routing {
        get("/login") {
            val loginController = LoginController(call)
            loginController.performLogin()
        }
    }
}
