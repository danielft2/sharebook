package com.example.sharebook.book_feature.presentation.form_book

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.Typography
import com.example.sharebook.core.presentation.ui.theme.green800
import java.io.File

@Composable
fun ImagesAdditional(
    onChange: (index: Int, file: File, isAdd: Boolean) -> Unit
) {
    val images = listOf(1, 2, 3, 4)

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Imagens Extras",
            color = green800,
            style = Typography.subtitle1
        )

        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(images) {index ->
                ImageFileInput { file, isAdd -> onChange(index, file, isAdd) }
            }
        }
    }

}