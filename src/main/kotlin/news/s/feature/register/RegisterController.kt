package news.s.feature.register

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import news.s.database.tokens.TokenDTO
import news.s.database.tokens.TokensModel
import news.s.database.users.UserDTO
import news.s.database.users.UserModel
import news.s.utils.isValidEmail
import org.jetbrains.exposed.exceptions.ExposedSQLException
import java.util.*

class RegisterController(private val call: ApplicationCall) {

    suspend fun registerNewUser() {

        val registerReceiveRemote = call.receive<RegisterReceiveRemote>()
        if (!registerReceiveRemote.login.isValidEmail()) {
            call.respond(HttpStatusCode.BadRequest, "Invalid email")
            }
            val userDTO = UserModel.fetchUser(registerReceiveRemote.login)

            if (userDTO != null) {
                call.respond(HttpStatusCode.Conflict, "User already exists")
            } else {
                val token = UUID.randomUUID().toString()

               try {
                   UserModel.insert(
                       UserDTO(
                           login = registerReceiveRemote.login,
                           password = registerReceiveRemote.password,
                           name = registerReceiveRemote.name,
                           privatename = registerReceiveRemote.privatename,
                           bio = " "
                       )
                   )
               } catch (e: ExposedSQLException){
                    call.respond(HttpStatusCode.Conflict, "User already exists2")
               }



                TokensModel.insert(
                    TokenDTO(
                        id = UUID.randomUUID().toString(),
                        login = registerReceiveRemote.login,
                        token = token
                    )
                )
                call.respond(RegisterResponseRemote(token = token))
            }


        }

    }
