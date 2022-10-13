package news.s.cache

import news.s.feature.register.RegisterReceiveRemote

data class TokenCache(
    val token: String,
    val login: String
)

object InMemoryCache {
    val userlist: MutableList<RegisterReceiveRemote> = mutableListOf()
    val token: MutableList<TokenCache> = mutableListOf()
}