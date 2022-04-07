package ru.netology

fun main() {
    val countSec = 7200

    val result = agoToText(countSec)
    println(result)
}

fun agoToText(countSec: Int): String {
    return when (countSec) {
        in 0..60 -> "был(а) в сети только что"
        in 61..60 * 60 -> {
            inMinutes(countSec)
        }
        in 60 * 60 + 1..24 * 60 * 60 -> {
            inHours(countSec)
        }
        in 24 * 60 * 60 + 1..2 * 24 * 60 * 60 -> "был(а) в сети сегодня"
        in 2 * 24 * 60 * 60 + 1..3 * 24 * 60 * 60 -> "был(а) в сети вчера"
        else -> "был(а) в сети давно"
    }
}

fun inMinutes(countSec: Int): String {
    val countMinutes = countSec / 60
    val minutesStr = when {
        (countMinutes == 1 || countMinutes % 10 == 1) && countMinutes != 11 -> "минуту назад"
        (countMinutes % 10 in 2..4) && (countMinutes !in 12..14) -> "минуты назад"
        else -> "минут"
    }
    return "был(а) в сети $countMinutes $minutesStr"
}

fun inHours(countSec: Int): String {
    val countHours = countSec / (60 * 60)
    val countHoursStr = countHours.toString()
    val countMinutesLastChar = countHoursStr.substring(countHoursStr.length - 1, countHoursStr.length)

    val hoursStr = if (countHoursStr.length == 1) {
        when (countMinutesLastChar) {
            "1" -> "час назад"
            "2", "3", "4" -> "часа назад"
            else -> "часов назад"
        }
    } else {
        val countHoursStrTwoLastChar = countHoursStr.substring(countHoursStr.length - 2, countHoursStr.length)

        when (countMinutesLastChar) {
            "1" -> {
                if (countHoursStrTwoLastChar != "11") "час"
                else "часов"
            }
            "2", "3", "4" -> "часа"
            else -> "часов"
        }
    }
    return "был(а) в сети $countHours $hoursStr"
}