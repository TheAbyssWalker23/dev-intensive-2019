package ru.skillbranch.devintensive.utils

object Utils {
    fun parseFullName(fullName:String?, divider : String = " ") : Pair<String?, String?> {
        val parts : List<String>? = fullName?.split(divider)

        var firstName = parts?.getOrNull(0)
        if (firstName == "")
            firstName = null

        var lastName = parts?.getOrNull(1)
        if (lastName == "")
            lastName = null

//        return Pair(firstName, lastName)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider : String = " ") : String {
        var result = payload.replace("а", "a" , true)
        result = result.replace("б", "b" , true)
        result = result.replace("в", "v" , true)
        result = result.replace("г", "g" , true)
        result = result.replace("д", "d" , true)
        result = result.replace("е", "e" , true)
        result = result.replace("ё", "e" , true)
        result = result.replace("ж", "zh", true)
        result = result.replace("з", "z" , true)
        result = result.replace("и", "i" , true)
        result = result.replace("й", "i" , true)
        result = result.replace("к", "k" , true)
        result = result.replace("л", "l" , true)
        result = result.replace("м", "m" , true)
        result = result.replace("н", "n" , true)
        result = result.replace("о", "o" , true)
        result = result.replace("п", "p" , true)
        result = result.replace("р", "r" , true)
        result = result.replace("с", "s" , true)
        result = result.replace("т", "t" , true)
        result = result.replace("у", "u" , true)
        result = result.replace("ф", "f" , true)
        result = result.replace("х", "h" , true)
        result = result.replace("ц", "c" , true)
        result = result.replace("ч", "ch", true)
        result = result.replace("ш", "sh", true)
        result = result.replace("щ", "sh", true)
        result = result.replace("ъ", ""  , true)
        result = result.replace("ы", "i" , true)
        result = result.replace("ь", ""  , true)
        result = result.replace("э", "e" , true)
        result = result.replace("ю", "yu", true)
        result = result.replace("я", "ya", true)

        var index = result.indexOf(" ")
        result = result.first().toUpperCase() + result.substring(1, index) + divider + result[index + 1].toUpperCase() + result.substring(index + 2)
        return result
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        var first = when (firstName) {
            null, " ", "" -> null
            else -> firstName!!.first()
        }
        var last = when (lastName) {
            null, " ", "" -> null
            else -> lastName!!.first()
        }
        return when (first) {
            null -> if (last == null) null else "$last".toUpperCase()
            else -> if (last == null) "$first".toUpperCase() else "$first$last".toUpperCase()
        }
    }
}