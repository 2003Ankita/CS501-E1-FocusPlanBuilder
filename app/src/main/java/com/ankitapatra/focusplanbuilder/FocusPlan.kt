package com.ankitapatra.focusplanbuilder

data class FocusPlan(
    val subject: String,
    val minutes: Int,
    val category: String,
    val breakMinutes: Int
)

fun durationCategory(minutes: Int): String {
    return when {
        minutes < 10 -> "Invalid"
        minutes in 10..29 -> "Quick review"
        minutes in 30..60 -> "Focused session"
        else -> "Extended session"
    }
}

fun recommendedBreak(minutes: Int): Int {
    return when {
        minutes in 10..29 -> 5
        minutes in 30..60 -> 10
        minutes > 60 -> 15
        else -> 0
    }
}