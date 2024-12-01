package com.example.geneticalgorithm.core.ext

import com.example.geneticalgorithm.core.models.HouseFeature


fun HouseFeature.Location.toAppropriateFormat(): String {
    val underScoreIndex = this.name.indexOf('_')
    if (underScoreIndex == -1) return this.name.toAppropriateFormat()
    val firstPart = this.name.substring(0, underScoreIndex)
    val secondPart = this.name.substring(underScoreIndex + 1, this.name.length)
    return firstPart.toAppropriateFormat() + " " + secondPart.toAppropriateFormat()
}

