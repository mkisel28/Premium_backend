package news.slivy.feature.register

import kotlinx.serialization.Serializable

@Serializable
data class RegisterReceiveRemote(
    val name: String,
    val privatename: String,
    val login: String,
    val password: String
)

@Serializable
data class RegisterResponseRemote(
    val token: String,
)