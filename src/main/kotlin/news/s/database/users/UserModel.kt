package news.s.database.users

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object UserModel: Table("users") {
    private val login = UserModel.varchar("login",25)
    private val password = UserModel.varchar("password",25)
    private val name = UserModel.varchar("name",32)
    private val privatename = UserModel.varchar("privatename",25)
    private val bio = UserModel.varchar("bio",100)

    fun insert(UserDTO: UserDTO) {
        transaction {
            UserModel.insert {
                it[login] = UserDTO.login
                it[password] = UserDTO.password
                it[name] = UserDTO.name
                it[privatename] = UserDTO.privatename
                it[bio] = UserDTO.bio ?: ""
            }

        }
    }

    fun fetchUser(login: String):UserDTO?{
        return try {
            transaction {
                val userModel = UserModel.select{ UserModel.login.eq(login) }.single()
                UserDTO(
                    login  = userModel[UserModel.login],
                    password = userModel[password],
                    name = userModel[name],
                    privatename = userModel[privatename],
                    bio = userModel[bio]
                )
            }
        } catch (e: Exception){
            null
        }
    }
}