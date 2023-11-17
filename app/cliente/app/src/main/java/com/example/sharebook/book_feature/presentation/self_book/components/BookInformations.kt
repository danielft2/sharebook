package com.example.sharebook.book_feature.presentation.self_book.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.book_feature.data.mock.model.BookDatailsMock
import com.example.sharebook.core.presentation.components.BookTag
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun BookInformations() {
    val bookDatails = BookDatailsMock()
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(108.dp)
                .height(160.dp)
                .clip(Shapes.medium)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.fillMaxWidth()) {
            Column {
                Text(
                    text = "${bookDatails.genero} - ${bookDatails.edicao}",
                    color = green500,
                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 1
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = bookDatails.titulo,
                    color = green900,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = bookDatails.autor,
                    color = gray500,
                    fontFamily = FontFamily(Font(R.font.inter_regular)),
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.height(12.dp))

                Column(modifier = Modifier.fillMaxWidth()) {
                    BookTag(text = bookDatails.status, background = blue100, colorText = blue500)
                    Spacer(modifier = Modifier.height(8.dp))
                    BookTag(text = bookDatails.podeBuscar, background = green100, colorText = green600)
                }
            }
        }
    }
}
