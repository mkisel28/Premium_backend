package news.slivy.feature.profile

import kotlinx.serialization.Serializable

@Serializable
data class ProfileReceiveRemote(
    val login: String
)

@Serializable
data class ProfileResponseRemote(
    val name: String,
    val privatename: String,
    val login: String,
    val bio: String?
    )
