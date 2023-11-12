package com.example.sharebook.book_feature.presentation.self_book.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.sharebook.auth_feature.presentation.register.RegisterViewModel
import com.example.sharebook.book_feature.presentation.self_book.SelfBookViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformSpanStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green100
import com.example.sharebook.core.presentation.ui.theme.green500
import com.example.sharebook.core.presentation.ui.theme.green600
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.book_feature.data.mock.model.BookDatailsMock
import com.example.sharebook.book_feature.data.mock.model.PedidoTrocaMock
import com.example.sharebook.book_feature.presentation.self_book.event.RequestBookEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun TextWithMoreButton(text: String) {
    var isExpanded by remember { mutableStateOf(false) }

    val firstPart = if (isExpanded) {
        text
    } else {
        text.take(150) + if (text.length > 150) " ..." else ""
    }

    Column {
        Text(text = firstPart)
        if (text.length > 150) {
            Button(onClick = { isExpanded = !isExpanded }) {
                Text(text = if (isExpanded) "Mostrar menos" else "Ler mais")
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Book(
    viewModel: SelfBookViewModel = hiltViewModel(),
    navController: NavController
) {
    val bookDatails = BookDatailsMock()
    val pedidoTroca = PedidoTrocaMock()
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        LazyColumn (
            modifier = Modifier
                .background(Color(0xFFF8F8F8))
        ) {
            items(count = 1) {
                Column (
                    modifier = Modifier
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    /* ******* Detalhes do livro ******* */
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(108.dp)
                                .height(160.dp)
                                .clip(RoundedCornerShape(6.dp))
                        )

                        Column(
                            modifier = Modifier.
                            padding(16.dp)
                        ) {
                            Row() {
                                Text(
                                    text = bookDatails.genero,
                                    color = green500,
                                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                                    fontWeight = FontWeight.SemiBold
                                )

                                Text(
                                    text = " - ",
                                    color = green500,
                                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                                )

                                Text(
                                    text = bookDatails.edicao,
                                    color = green500,
                                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = bookDatails.titulo,
                                color = green900,
                                fontFamily = FontFamily(Font(R.font.lato_bold)),
                                fontSize = 14.sp
                            )

                            Spacer(modifier = Modifier.height(4.dp))

                            Text(
                                text = bookDatails.autor,
                                color = gray500,
                                fontFamily = FontFamily(Font(R.font.inter_regular)),
                                fontSize = 12.sp
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Box(
                                modifier = Modifier
                                    .background(Color(0xFFD4EAFF), shape = Shapes.medium)
                                    .padding(vertical = 4.dp, horizontal = 16.dp)
                            ) {
                                Text(
                                    text = bookDatails.status,
                                    fontFamily = FontFamily(Font(R.font.inter_semibold)),
                                    fontSize = 11.sp,
                                    color = Color(0xFF005B9D),
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            Box(
                                modifier = Modifier
                                    .background(green100, shape = Shapes.medium)
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

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(gray200)
                    ) {}

                    Spacer(modifier = Modifier.height(20.dp))

                    /* ******* Descrição do Livro ******* */
                    Column {
                        Text(
                            text = stringResource(id = R.string.book_datails_descreption_session),
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 14.sp,
                            color = green900,
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextWithMoreButton(text = bookDatails.descricao)
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp)
                            .background(gray200)
                    ) {}

                    /* ******* Galeria de Imagens ******* */
                    Column {
                        Spacer(modifier = Modifier.height(20.dp))

                        Text(
                            text = stringResource(id = R.string.book_datails_gallery_session),
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 14.sp,
                            color = green900
                        )

                        Spacer(modifier = Modifier.height(14.dp))

                        LazyRow {
                            items(count = 1) {
                                Image(
                                    painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes_foto1),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(146.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Image(
                                    painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes_foto2),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(146.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Image(
                                    painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes_foto3),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(146.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Image(
                                    painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes_foto4),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(100.dp)
                                        .height(146.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                /* ******* Lista de Solicitações ******* */
                Column (
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.book_datails_requests_session),
                        fontFamily = FontFamily(Font(R.font.lato_bold)),
                        fontSize = 14.sp,
                        color = green900
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(44.dp)
                                .height(44.dp)
                                .clip(RoundedCornerShape(22.dp))
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column (
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = pedidoTroca.nomePessoa,
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontSize = 12.sp,
                                color = green900
                            )

                            Text(
                                text = pedidoTroca.localPessoa,
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontSize = 11.sp,
                                color = gray500
                            )
                        }

                        Spacer(modifier = Modifier.width(48.dp))

                        Button(
                            onClick = {},
                            shape = Shapes.medium,
                            modifier = Modifier
                                .height(36.dp)
                                .width(84.dp)
                                .padding(0.dp)
                                .align(Alignment.CenterVertically),
                            colors = ButtonDefaults.buttonColors(green500)
                        ){
                            Text(
                                text = stringResource(id = R.string.book_datails_accept_button),
                                color = white,
                                fontFamily = Lato,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button (
                            onClick = {},
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .height(36.dp)
                                .width(84.dp),
                            colors = ButtonDefaults.buttonColors(gray200)
                        ) {
                            Text(
                                text = stringResource(id = R.string.book_datails_refuse_button),
                                fontFamily = Lato,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = green900
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(44.dp)
                                .height(44.dp)
                                .clip(RoundedCornerShape(22.dp))
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column (
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = pedidoTroca.nomePessoa,
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontSize = 12.sp,
                                color = green900
                            )

                            Text(
                                text = pedidoTroca.localPessoa,
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontSize = 11.sp,
                                color = gray500
                            )
                        }

                        Spacer(modifier = Modifier.width(48.dp))

                        Button(
                            onClick = {viewModel.onEvent(RequestBookEvent.AcceptRequest)},
                            shape = Shapes.medium,
                            modifier = Modifier
                                .height(36.dp)
                                .width(84.dp)
                                .padding(0.dp)
                                .align(Alignment.CenterVertically),
                            colors = ButtonDefaults.buttonColors(green500)
                        ){
                            Text(
                                text = stringResource(id = R.string.book_datails_accept_button),
                                color = white,
                                fontFamily = Lato,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button (
                            onClick = {viewModel.onEvent(RequestBookEvent.RefuseRequest)},
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .height(36.dp)
                                .width(84.dp),
                            colors = ButtonDefaults.buttonColors(gray200)
                        ) {
                            Text(
                                text = stringResource(id = R.string.book_datails_refuse_button),
                                fontFamily = Lato,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = green900
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row {
                        Image(
                            painter = painterResource(id = R.drawable.profile),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(44.dp)
                                .height(44.dp)
                                .clip(RoundedCornerShape(22.dp))
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Column (
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {
                            Text(
                                text = pedidoTroca.nomePessoa,
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontSize = 12.sp,
                                color = green900
                            )

                            Text(
                                text = pedidoTroca.localPessoa,
                                fontFamily = FontFamily(Font(R.font.inter_medium)),
                                fontSize = 11.sp,
                                color = gray500
                            )
                        }

                        Spacer(modifier = Modifier.width(48.dp))

                        Button(
                            onClick = {viewModel.onEvent(RequestBookEvent.AcceptRequest)},
                            shape = Shapes.medium,
                            modifier = Modifier
                                .height(36.dp)
                                .width(84.dp)
                                .padding(0.dp)
                                .align(Alignment.CenterVertically),
                            colors = ButtonDefaults.buttonColors(green500)
                        ){
                            Text(
                                text = stringResource(id = R.string.book_datails_accept_button),
                                color = white,
                                fontFamily = Lato,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button (
                            onClick = {viewModel.onEvent(RequestBookEvent.RefuseRequest)},
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .height(36.dp)
                                .width(84.dp),
                            colors = ButtonDefaults.buttonColors(gray200)
                        ) {
                            Text(
                                text = stringResource(id = R.string.book_datails_refuse_button),
                                fontFamily = Lato,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = green900
                            )
                        }
                    }
                }
            }
        }
    }
}