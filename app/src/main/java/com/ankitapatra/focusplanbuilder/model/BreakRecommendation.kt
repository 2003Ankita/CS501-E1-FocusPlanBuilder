package com.ankitapatra.focusplanbuilder.model

fun recommendedBreak(minutes: Int): Int {
    return when {
        minutes in 10..29 -> 5
        minutes in 30..60 -> 10
        minutes > 60 -> 15
        else -> 0
    }
}