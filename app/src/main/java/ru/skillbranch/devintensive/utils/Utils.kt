package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName: String?, divider: String = " "): Pair<String?, String?> {
        val parts: List<String>? = fullName?.split(divider)

        var firstName = parts?.getOrNull(0)
        if (firstName == "")
            firstName = null

        var lastName = parts?.getOrNull(1)
        if (lastName == "")
            lastName = null

//        return Pair(firstName, lastName)
        return firstName to lastName
    }

    private val map: HashMap<Char, String>
        get() = hashMapOf(
                'а' to "a",
                'б' to "b",
                'в' to "v",
                'г' to "g",
                'д' to "d",
                'е' to "e",
                'ё' to "e",
                'ж' to "zh",
                'з' to "z",
                'и' to "i",
                'й' to "i",
                'к' to "k",
                'л' to "l",
                'м' to "m",
                'н' to "n",
                'о' to "o",
                'п' to "p",
                'р' to "r",
                'с' to "s",
                'т' to "t",
                'у' to "u",
                'ф' to "f",
                'х' to "h",
                'ц' to "c",
                'ч' to "ch",
                'ш' to "sh",
                'щ' to "sh'",
                'ъ' to "",
                'ы' to "i",
                'ь' to "",
                'э' to "e",
                'ю' to "yu",
                'я' to "ya"
        )

    fun transliteration(payload: String, divider: String = " "): String {
        var result = ""
        payload.replace(" ", divider)
                .toCharArray()
                .forEach {
                    val symbol = map[it.toLowerCase()]
                    result += when {
                        symbol == null -> it
                        it.isUpperCase() -> symbol.capitalize()
                        else -> symbol
                    }
                }
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var first = when (firstName) {
            null, " ", "" -> null
            else -> firstName.first()
        }
        var last = when (lastName) {
            null, " ", "" -> null
            else -> lastName.first()
        }
        return when (first) {
            null -> if (last == null) null else "$last".toUpperCase()
            else -> if (last == null) "$first".toUpperCase() else "$first$last".toUpperCase()
        }
    }
}