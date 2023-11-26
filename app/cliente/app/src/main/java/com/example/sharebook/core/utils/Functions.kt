package com.example.sharebook.core.utils

import java.util.*

object Functions {
    fun userNameAvatar(name: String): String {
        val fisrtLetter = name.split("")[1].uppercase(Locale.ROOT)
        val secondLetter = name.split("")[2].uppercase(Locale.ROOT)

        return "${fisrtLetter}${secondLetter}"
    }

    fun userNameAccount(name: String): String {
        val nameCapitalize =  name.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.ROOT
            ) else it.toString()
        }

        return nameCapitalize
    }


    fun getValuesFromList(args: List<String>): String {
        var value = ""
        args.forEachIndexed { index, author ->
            value += if (index == args.lastIndex) author
            else "$author, "
        }

        return value
    }
}

