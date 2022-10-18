package news.slivy.security.hashing

import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import java.security.MessageDigest

class toMD5 {
    fun Create(pass:String): String {
        val config = HoconApplicationConfig(ConfigFactory.load())
        val toCod = pass+ config.property("jwt.salt")
        val bytes = MessageDigest.getInstance("MD5").digest(toCod.toByteArray())
        return bytes.toHex()
    }

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}