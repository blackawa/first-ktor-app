package jp.blackawa.example.firstktorapp

import azadev.kotlin.css.Stylesheet
import kotlinx.html.*
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.html.*
import io.ktor.http.ContentType
import io.ktor.pipeline.*
import io.ktor.response.respondText
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import jp.blackawa.example.firstktorapp.view.style

fun main(args: Array<String>) {
    embeddedServer(Jetty, port = 8080) {
        install(DefaultHeaders)
        install(CallLogging)
        install(Routing) {
            get("/") {
                call.respondHtml {
                    head {
                        title {
                            +"Html Application"
                        }
                        link("/style.css", "stylesheet")
                    }
                    body {
                        h1 {
                            +"Sample Application with HTML builders."
                        }
                        widget {
                            +"Widgets are just functions."
                        }
                    }
                }
            }
            get("/style.css") {
                call.respondText(
                    style(),
                    ContentType.Text.CSS
                )
            }
        }
    }.start(wait = true)
}

fun FlowContent.widget(body: FlowContent.() -> Unit) {
    div { body() }
}

