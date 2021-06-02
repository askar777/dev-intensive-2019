package ru.skillbranch.devintensive.extensions

fun String.truncate(limit: Int = 16) = if (this.length > limit) {
    "${this.substring(0, limit).trim()}..."
} else {
    this
}

fun String.stripHtml() = replace("<.*?>".toRegex(),"").replace("\\s+".toRegex(), " ")