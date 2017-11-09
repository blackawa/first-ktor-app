package jp.blackawa.example.firstktorapp.view

import jp.blackawa.example.firstktorapp.infrastructure.Entry
import kotlinx.html.*

fun index(entries: List<Entry>): HTML.() -> Unit = {
    head {
        title {
            +"Sample blog application"
        }
        link("/style.css", "stylesheet")
    }
    body {
        h1 {
            +"Sample blog"
        }
        ul {
            entries.forEach { e ->
                widget {
                    +e.title
                }
            }
        }
    }
}

fun UL.widget(body: FlowContent.() -> Unit) {
    li { body() }
}

