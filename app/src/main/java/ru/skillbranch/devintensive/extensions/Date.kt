package ru.skillbranch.devintensive.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY

    }
    this.time = time
    return this
}

fun Date.humanizeDiff(): String {
    val timeDiff = Date().time - this.time
    if (timeDiff >= 0) {
        return when (timeDiff) {
            in 0 until 2 * SECOND -> "только что"
            in SECOND until 45 * SECOND -> "несколько секунд назад"
            in 45 * SECOND until 75 * SECOND -> "минуту назад"
            in 75 * SECOND until 45 * MINUTE -> "${timeDiff / MINUTE} минут назад"
            in 45 * MINUTE until 75 * MINUTE -> "час назад"
            in 75 * MINUTE until 5 * HOUR -> "${timeDiff / HOUR} часа назад"
            in 5 * HOUR until 22 * HOUR -> "${timeDiff / HOUR} часов назад"
            in 22 * HOUR until 26 * HOUR -> "день назад"
            in 26 * HOUR until 360 * DAY -> "${timeDiff / DAY} дней назад"
            else -> "более года назад"
        }
    } else {
        return when (val diffTime = this.time - Date().time) {
            in 0 until 45 * SECOND -> "через ${diffTime / SECOND} секунды"
            in 45 * SECOND until 75 * SECOND -> "через минуту"
            in 75 * SECOND until 45 * MINUTE -> "через ${diffTime / MINUTE} минуты"
            in 45 * MINUTE until 75 * MINUTE -> "через час"
            in 75 * MINUTE until 5 * HOUR -> "через ${diffTime / HOUR} часа"
            in 5 * HOUR until 22 * HOUR -> "через ${diffTime / HOUR} часов"
            in 22 * HOUR until 26 * HOUR -> "через день"
            in 26 * HOUR until 360 * DAY -> "через ${diffTime / DAY} дней"
            else -> "более чем через год"
        }
    }
}


enum class TimeUnits {
    SECOND, MINUTE, HOUR, DAY;

    fun plural(value: Int): String {
        return when {
            value <= 1 && this == SECOND -> "$value секунду"
            value > 1 && this == SECOND -> "$value секунды"
            value <= 1 && this == MINUTE -> "$value минуту"
            value > 1 && this == MINUTE -> "$value минуты"
            value <= 1 && this == HOUR -> "$value часу"
            value in 2..4 -> "$value часа"
            value > 5 && this == HOUR -> "$value часов"
            value <= 1 && this == DAY -> "$value день"
            else -> "$value дня"
        }
    }
}