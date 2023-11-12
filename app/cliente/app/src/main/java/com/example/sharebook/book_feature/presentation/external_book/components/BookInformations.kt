package com.example.sharebook.book_feature.presentation.external_book.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.sharebook.book_feature.data.mock.model.PedidoTrocaMock
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun BookInformations() {
    val bookDatails = BookDatailsMock()
    val pedidoTroca = PedidoTrocaMock()

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

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .background(color = blue100, shape = Shapes.medium)
                            .padding(vertical = 4.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            text = bookDatails.status,
                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
                            fontSize = 11.sp,
                            color = blue500,
                        )
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier
                            .background(green100, shape = Shapes.small)
                            .padding(vertical = 4.dp, horizontal = 16.dp)
                    ) {
                        Text(
                            text = bookDatails.podeBuscar,
                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
                            fontSize = 11.sp,
                            color = green600
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            Row {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp)
                        .clip(RoundedCornerShape(22.dp))
                )

                Spacer(modifier = Modifier.width(8.dp))

                Column (modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(
                        text = pedidoTroca.nomePessoa,
                        fontFamily = Inter,
                        fontSize = 12.sp,
                        color = green900,
                        fontWeight = FontWeight.Medium
                    )

                    Text(
                        text = pedidoTroca.localPessoa,
                        fontFamily = Inter,
                        fontSize = 12.sp,
                        color = gray500,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
