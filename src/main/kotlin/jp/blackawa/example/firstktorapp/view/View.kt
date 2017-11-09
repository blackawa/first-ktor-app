package jp.blackawa.example.firstktorapp.view

import kotlinx.html.*

fun index(): HTML.() -> Unit = {
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

fun FlowContent.widget(body: FlowContent.() -> Unit) {
    div { body() }
}

