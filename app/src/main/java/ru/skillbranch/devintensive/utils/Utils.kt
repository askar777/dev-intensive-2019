package ru.skillbranch.devintensive.utils

import java.util.*

object Utils {
    fun parseFullName(fullName:String?):Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")

        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    fun transliteration(payload: String, divider:String = " "): String {
        var result = payload
        result.forEach {
            val char = it.toString()
            if(map.containsKey(char)) {
                result = result.replace(char, map.getValue(char))
            }
        }
        return result.split(" ").joinToString(separator = divider)
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstUpper = firstName?.take(1)?.trim()?.toUpperCase(Locale.ROOT)
        val secondUpper = lastName?.take(1)?.trim()?.toUpperCase(Locale.ROOT)
        return when {
            firstUpper == null-> secondUpper
            secondUpper == null -> firstUpper
            firstUpper.isNullOrEmpty() && secondUpper.isNullOrEmpty() -> null
            else -> firstUpper + secondUpper
        }
    }

    val map = mapOf (
            "а" to "a", "б" to "b", "в" to "v",
            "г" to "g", "д" to "d", "е" to "e",
            "ё" to "e", "ж" to "zh", "з" to "z",
            "и" to "i", "й" to "i", "к" to "k",
            "л" to "l", "м" to "m", "н" to "n",
            "о" to "o", "п" to "p", "р" to "r",
            "с" to "s", "т" to "t", "у" to "u",
            "ф" to "f", "х" to "h", "ц" to "c",
            "ч" to "ch", "ш" to "sh", "щ" to "sh'",
            "ъ" to "", "ы" to "i", "ь" to "",
            "э" to "e", "ю" to "yu", "я" to "ya",

            "А" to "A", "Б" to "B", "В" to "V",
            "Г" to "G", "Д" to "D", "Е" to "E",
            "Ё" to "E", "Ж" to "Zh", "З" to "Z",
            "И" to "I", "Й" to "I", "К" to "K",
            "Л" to "L", "М" to "M", "Н" to "N",
            "О" to "O", "П" to "P", "Р" to "R",
            "С" to "S", "Т" to "T", "У" to "U",
            "Ф" to "F", "Х" to "H", "Ц" to "C",
            "Ч" to "Ch", "Ш" to "Sh", "Щ" to "Sh'", "Ы" to "I",
            "Э" to "E", "Ю" to "Yu", "Я" to "Ya"
    )

    
}