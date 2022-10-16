package news.slivy.feature.login

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import news.slivy.database.tokens.TokenDTO
import news.slivy.database.tokens.TokensModel
import news.slivy.database.users.UserModel
import java.util.*

class LoginController(private val call: ApplicationCall) {

   suspend fun performLogin(){
        val receive =  call.receive<LoginReceiveRemote>()
        val userDTO = UserModel.fetchUser(receive.login)

        if (userDTO == null)
        {
            call.respond(HttpStatusCode.BadRequest, "User not found")
        }
        else
        {
            if (userDTO.password == receive.password)
            {
                val token = UUID.randomUUID().toString()
                TokensModel.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        login = receive.login,
                        token = token
                    )
                )
                call.respond(LoginResponseRemote(token = token))
            }
            else
            {
                call.respond(HttpStatusCode.BadRequest, "Invalid password")
            }
        }

        call.respond(HttpStatusCode.BadRequest, "sd")
    }
}


