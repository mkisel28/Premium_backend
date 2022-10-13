package news.s.database.tokens

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

object TokensModel: Table("tokens") {
    private val id = TokensModel.varchar("id", 60)
    private val login = TokensModel.varchar("login", 25)
    private val token = TokensModel.varchar("token", 60)

    fun insert(tokenDTO: TokenDTO) {
        transaction {
            TokensModel.insert {
                it[id] = tokenDTO.id
                it[login] = tokenDTO.login
                it[token] = tokenDTO.token
            }

        }
    }

}