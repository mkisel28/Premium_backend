package news.slivy.feature.profile

import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.configureProfileRouting() {

    routing {
        post("/profileInfo") {
            val profileController = ProfileController(call)
            profileController.takeProfileInfo()
        }
    }
}
