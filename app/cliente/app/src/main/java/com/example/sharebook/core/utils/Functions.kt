package com.example.sharebook.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.presentation.ui.theme.*
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

    fun getPreference(podeBuscar: Boolean): BookPreferenceTag {
        return if (podeBuscar) BookPreferenceTag.SEARCH
        else BookPreferenceTag.RECEIVE
    }

    @Composable
    fun getColorsByRequestStatus(status: BookRequestStatus): RequestStatusColros {
        when (status) {
            BookRequestStatus.SEND -> {
                return RequestStatusColros(
                    colorText = blue500,
                    background = blue100
                )
            }
            BookRequestStatus.CANCEL -> {
                return RequestStatusColros(
                    colorText = blue100,
                    background = blue500
                )
            }
            BookRequestStatus.FINALIZE -> {
                return RequestStatusColros(
                    colorText = green600,
                    background = green100
                )
            }
            else -> {
                return RequestStatusColros(
                    colorText = gray800,
                    background = gray200
                )
            }
        }
    }
}

data class RequestStatusColros(
    val colorText: Color,
    val background: Color
)