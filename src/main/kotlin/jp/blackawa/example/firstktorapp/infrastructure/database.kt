package jp.blackawa.example.firstktorapp.infrastructure

import org.jetbrains.squash.connection.DatabaseConnection
import org.jetbrains.squash.connection.transaction
import org.jetbrains.squash.definition.*
import org.jetbrains.squash.dialects.sqlite.SqLiteConnection
import org.jetbrains.squash.query.select
import org.jetbrains.squash.results.get

class Database {
    private val connection: DatabaseConnection

    init {
        connection = SqLiteConnection { org.sqlite.SQLiteConnection("db", "development.sqlite3") }
        connection.transaction {
            databaseSchema().create(listOf(Entries))
        }
    }

    fun findAllEntries(): List<Entry> = connection.transaction {
        Entries.select(Entries.id, Entries.title, Entries.content).execute().map {
            Entry(id = it.get(0), title = it.get(1), content = it.get(2))
        }.toList()
    }
}

data class Entry(val id: Long, val title: String, val content: String)

object Entries : TableDefinition() {
    val id = integer("id").autoIncrement().primaryKey()
    val title = varchar("title", 120)
    val content = varchar("content", 10000)
}