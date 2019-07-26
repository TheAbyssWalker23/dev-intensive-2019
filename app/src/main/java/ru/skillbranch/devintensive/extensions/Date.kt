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

    return when {
        date.time <= -360 * DAY -> "более чем через год"
        date.time <= -26 * HOUR -> "через ${TimeUnits.DAY.plural(-(date.time / DAY).toInt())}"
        date.time <= -22 * HOUR -> "через день"
        date.time <= -75 * MINUTE -> "через ${TimeUnits.HOUR.plural(-(date.time / HOUR).toInt())}"
        date.time <= -45 * MINUTE -> "через час"
        date.time <= -75 * SECOND -> "через ${TimeUnits.MINUTE.plural(-(date.time / MINUTE).toInt())}"
        date.time <= -45 * SECOND -> "через минуту"
        date.time <= -SECOND -> "через несколько секунд"
        date.time <= SECOND -> "только что"
        date.time <= 45 * SECOND -> "несколько секунд назад"
        date.time <= 75 * SECOND -> "минуту назад"
        date.time <= 45 * MINUTE -> "${TimeUnits.MINUTE.plural((date.time / MINUTE).toInt())} назад"
        date.time <= 75 * MINUTE -> "час назад"
        date.time <= 22 * HOUR -> "${TimeUnits.HOUR.plural((date.time / HOUR).toInt())} назад"
        date.time <= 26 * HOUR -> "день назад"
        date.time <= 360 * DAY -> "${TimeUnits.DAY.plural((date.time / DAY).toInt())} назад"
        else -> "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return when (this) {
            SECOND -> "$value секунд${if (value % 10 == 1 && value % 100 != 11) "у" else if (value % 10 in 2..4 && (value < 11 || value > 14)) "ы" else ""}"
            MINUTE -> "$value минут${if (value % 10 == 1 && value % 100 != 11) "у" else if (value % 10 in 2..4 && (value < 11 || value > 14)) "ы" else ""}"
            HOUR -> "$value час${if (value % 10 == 1 && value % 100 != 11) "" else if (value % 10 in 2..4 && (value < 11 || value > 14)) "а" else "ов"}"
            DAY -> "$value ${if (value % 10 == 1 && value % 100 != 11) "день" else if (value % 10 in 2..4 && (value < 11 || value > 14)) "дня" else "дней"}"
        }
    }
}