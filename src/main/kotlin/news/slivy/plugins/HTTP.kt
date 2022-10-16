package news.slivy.plugins

import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.application.*

fun Application.configureHTTP() {
    install(DefaultHeaders) {
        header("X-Engine", "pidor")
        header("Server", "pidors")
         // will send this header with each response
    }

}
