package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern : String = "HH:mm:ss dd.MM.yy") : String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
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

fun Date.humanizeDiff(date: Date = Date()): String {
    date.time -= this.time

    var result : String = when {
        date.time <= -360 * DAY -> "более чем через год"
        date.time <= -26 * HOUR -> "через ${date.format("dd")} дней"
        date.time <= -22 * HOUR -> "через день"
        date.time <= -75 * MINUTE -> "${date.format("HH")} часов"
        date.time <= -45 * MINUTE -> "через час"
        date.time <= -75 * SECOND -> "через ${date.format("mm")} минуты"
        date.time <= -45 * SECOND -> "через минуту"
        date.time <= -SECOND -> "через несколько секунд"
        date.time <= SECOND -> "только что"
        date.time <= 45 * SECOND -> "несколько секунд назад"
        date.time <= 75 * SECOND -> "минуту назад"
        date.time <= 45 * MINUTE -> "${date.format("mm")} минуты назад"
        date.time <= 75 * MINUTE -> "час назад"
        date.time <= 22 * HOUR -> "${date.format("HH")} часов назад"
        date.time <= 26 * HOUR -> "день назад"
        date.time <= 360 * DAY -> "${date.format("dd")} дней назад"
        else -> "более года назад"
    }

    return result
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}