package com.example.sharebook.core.utils

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.core.content.FileProvider
import com.example.sharebook.BuildConfig
import com.example.sharebook.core.domain.enum.BookPreferenceTag
import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.presentation.ui.theme.*
import java.io.File
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

    fun getStatusByName(name: String): BookRequestStatus {
        return when (name) {
            BookRequestStatus.SEND.tag -> BookRequestStatus.SEND
            BookRequestStatus.FINALIZE.tag -> BookRequestStatus.FINALIZE
            BookRequestStatus.CANCEL.tag -> BookRequestStatus.CANCEL
            BookRequestStatus.ACCEPTED.tag -> BookRequestStatus.ACCEPTED
            else -> { BookRequestStatus.SEND }
        }
    }

    fun Context.createTempPictureUri(
        provider: String = "${BuildConfig.APPLICATION_ID}.provider",
        fileName: String = "picture_${System.currentTimeMillis()}",
        fileExtension: String = ".png"
    ): Uri {
        val tempFile = File.createTempFile(
            fileName, fileExtension, cacheDir
        ).apply {
            createNewFile()
        }

        return FileProvider.getUriForFile(applicationContext, provider, tempFile)
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
            BookRequestStatus.ACCEPTED -> {
                return RequestStatusColros(
                    colorText = green600,
                    background = green100
                )
            }
            BookRequestStatus.FINALIZE -> {
                return RequestStatusColros(
                    colorText = green600,
                    background = green100
                )
            }
            BookRequestStatus.CANCEL -> {
                return RequestStatusColros(
                    colorText = orange700,
                    background = orange100
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

    fun createTmpFileFromUri(context: Context, uri: Uri, fileName: String): File? {
        return try {
            val stream = context.contentResolver.openInputStream(uri)
            val file = File.createTempFile(fileName, "file", context.cacheDir)
            org.apache.commons.io.FileUtils.copyInputStreamToFile(stream,file)
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

data class RequestStatusColros(
    val colorText: Color,
    val background: Color
)