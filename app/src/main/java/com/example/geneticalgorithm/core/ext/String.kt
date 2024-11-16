package com.example.geneticalgorithm.core.ext

fun String.toAppropriateFormat(): String {
    return this.first().uppercase() + this.substring(1).lowercase()
}

