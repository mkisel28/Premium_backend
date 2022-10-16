package news.slivy.feature.profile

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import news.slivy.database.users.UserDTO
import news.slivy.database.users.UserModel

class ProfileController(private val call: ApplicationCall) {

   suspend fun takeProfileInfo(){

       val receive =  call.receive<ProfileReceiveRemote>()
       val userDTO = UserModel.fetchUser(receive.login)

       if (userDTO == null)
       {
           call.respond(HttpStatusCode.BadRequest, "User not found")
       }
       else
       {
           if (userDTO.login == receive.login)
           {
               call.respond(ProfileResponseRemote(
                   name = userDTO.name,
                   privatename = userDTO.privatename,
                   login = userDTO.login,
                   bio = userDTO.bio
                   )
               )
           }
           else
           {
               call.respond(HttpStatusCode.BadRequest, "Invalid password")
           }
       }

       call.respond(HttpStatusCode.BadRequest, "sd")
    }
}


