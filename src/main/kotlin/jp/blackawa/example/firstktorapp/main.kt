package jp.blackawa.example.firstktorapp

import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.DefaultHeaders
import io.ktor.html.respondHtml
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import jp.blackawa.example.firstktorapp.infrastructure.Database
import jp.blackawa.example.firstktorapp.view.index
import jp.blackawa.example.firstktorapp.view.style

fun main(args: Array<String>) {
    val server = embeddedServer(Jetty, 8080) {
        val database = Database()
        install(DefaultHeaders)
        install(CallLogging)
        install(Routing) {
            get("/") {
                database.findAllEntries()
                call.respondHtml(HttpStatusCode.OK, index(database.findAllEntries()))
            }
            get("/style.css") {
                call.respondText(style(), ContentType.Text.CSS)
            }
        }
    }
    server.start(wait = true)
}
