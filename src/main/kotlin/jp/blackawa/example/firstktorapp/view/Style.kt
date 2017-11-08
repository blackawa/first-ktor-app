package jp.blackawa.example.firstktorapp.view

import azadev.kotlin.css.Stylesheet
import azadev.kotlin.css.color

fun style(): String =
    Stylesheet {
        h1 {
            color = 0x2e2e2e
        }
    }.render()
