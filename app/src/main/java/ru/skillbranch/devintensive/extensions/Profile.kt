package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.Profile
import ru.skillbranch.devintensive.utils.Utils

fun Profile.getNickName(): String {
    var fullName: String = when (firstName) {
        "", " ", null -> ""
        else -> firstName
    }

    fullName += when (lastName) {
        "", " ", null -> ""
        else -> "${if (fullName.isEmpty().not()) " " else ""}$lastName"
    }

    return Utils.transliteration(fullName, "_")
}