package ru.skillbranch.devintensive.extensions

fun String.truncate(length: Int = 16): String {
    return "${if (this.trim().length > length) "${this.substring(0, length).trim()}..." else this.trim()}"
}

fun String.stripHtml(): String {
    var temp = Regex("""(\s{2,})""").replace(this, " ")
    return Regex("""(<.*?>)|(&.+?;)""").replace(temp, "")
}