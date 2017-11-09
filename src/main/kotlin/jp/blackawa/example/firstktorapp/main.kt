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
import jp.blackawa.example.firstktorapp.view.index
import jp.blackawa.example.firstktorapp.view.style

fun main(args: Array<String>) {
    embeddedServer(Jetty, port = 8080) {
        install(DefaultHeaders)
        install(CallLogging)
        install(Routing) {
            get("/") {
                call.respondHtml(HttpStatusCode.OK, index())
            }
            get("/style.css") {
                call.respondText(style(), ContentType.Text.CSS)
            }
        }
    }.start(wait = true)
}

