package com.example.geneticalgorithm.core.models

data class FilterItemData(
    val text: String,
    val number: Int,
    var isChecked: Boolean,
    val type: Int = 0
)
