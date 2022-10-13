package news.slivy.feature.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import news.slivy.cache.InMemoryCache
import news.slivy.cache.TokenCache
import news.slivy.feature.login.LoginReceiveRemote
import news.slivy.feature.login.LoginResponseRemote
import news.slivy.utils.isValidEmail
import java.util.*

fun Application.configureRegisterRouting() {

    routing {
        post("/register") {
            val registerController = RegisterController(call)
            registerController.registerNewUser()


        }
    }
}
