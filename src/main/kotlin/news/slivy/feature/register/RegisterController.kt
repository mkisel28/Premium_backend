package news.slivy.feature.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import news.slivy.database.tokens.TokenDTO
import news.slivy.database.tokens.TokensModel
import news.slivy.database.users.UserDTO
import news.slivy.database.users.UserModel
import news.slivy.security.hashing.toMD5
import news.slivy.utils.isValidEmail
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser()
    {

        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.login.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Invalid email") }

        val userDTO = UserModel.fetchUser(registerReceiveRemote.login)
        if (userDTO != null) {
            call.respond(HttpStatusCode.Conflict, "User already exists") }
        else
        {
            val token = UUID.randomUUID().toString()
            try
            {
                UserModel.insert(UserDTO(
                        login = registerReceiveRemote.login,
                        password = toMD5().Create(registerReceiveRemote.password),
                        name = registerReceiveRemote.name,
                        privatename = registerReceiveRemote.privatename,
                        bio = ""))
            }
            catch (e: ExposedSQLException)
            {
                call.respond(HttpStatusCode.Conflict, "User already exists2")
            }


            TokensModel.insert(TokenDTO(
                    id = UUID.randomUUID().toString(),
                    login = registerReceiveRemote.login,
                    token = token)
            )

            call.respond(RegisterResponseRemote(token = token))
        }


    }

}
