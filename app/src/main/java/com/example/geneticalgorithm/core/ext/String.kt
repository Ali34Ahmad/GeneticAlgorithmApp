package com.example.geneticalgorithm.core.ext

import android.location.Location

fun String.toAppropriateFormat(): String {
    return this.first().uppercase() + this.substring(1).lowercase()
}


fun String.toEnumName(): String {
    var newString=this.uppercase()
    newString=newString.replace(' ','_')
    return newString
}

fun String.toNumberOfRoomsEnumName(): String{
    val numberOfRooms = this.substringBefore(" Room")
    val numberOfRoomsText ="R$numberOfRooms"
    return numberOfRoomsText
}
