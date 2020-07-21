package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.utils.Utils

fun Profile.getNickName(): String {
    var fullName: String = when (firstName) {
        "", " " -> ""
        else -> firstName
    }

    fullName += when (lastName) {
        "", " " -> ""
        else -> "${if (fullName.isEmpty().not()) " " else ""}$lastName"
    }

    return Utils.transliteration(fullName, "_")
}