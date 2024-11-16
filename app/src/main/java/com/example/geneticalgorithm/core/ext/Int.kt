package com.example.geneticalgorithm.core.ext

fun Int.toOrdinal(): String {
    val suffix = when (this % 10) {
        1 -> if (this % 100 != 11) "st" else "th"
        2 -> if (this % 100 != 12) "nd" else "th"
        3 -> if (this % 100 != 13) "rd" else "th"
        else -> "th"
    }
    return this.toString() + suffix
}