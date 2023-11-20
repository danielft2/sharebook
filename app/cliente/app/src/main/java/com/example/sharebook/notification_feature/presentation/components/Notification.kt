package com.example.sharebook.notification_feature.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.DividerCustom
import com.example.sharebook.core.presentation.components.HeaderWithBackground
import com.example.sharebook.core.presentation.components.IconButtonAction
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green500
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.core.utils.UiText
import com.example.sharebook.notification_feature.presentation.NotificationViewModel

data class dadosPessoa (
    val nomePessoa: String = "Ingrid de Souza",
    val textoNotificacao: String = "Solicitou para trocar livro com você. ",
    val horario: String = "22h"
)

@Composable
fun Notification(
    viewModel: NotificationViewModel = hiltViewModel(),
    navController: NavController
) {
    val notificaoDados = dadosPessoa()
    val notificacaoDados2 = dadosPessoa(textoNotificacao = "Recusou sua solicitação de troca de livro. ", horario = "23h")
    val textNotificaiton = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontFamily = FontFamily(Font(R.font.inter_medium)),
            fontSize = 12.sp,
            color = green500
        )) {
            append(notificaoDados.nomePessoa)
        }
        append(", ")
        withStyle(
            style = SpanStyle(
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontSize = 12.sp,
                        color = green900
                    )
        ) {
            append(notificaoDados.textoNotificacao)
        }
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                fontSize = 12.sp,
                color = gray500
            )
        ) {
            append(notificaoDados.horario)
        }
    }
    val textNotificaiton2 = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontFamily = FontFamily(Font(R.font.inter_medium)),
            fontSize = 12.sp,
            color = green500
        )) {
            append(notificacaoDados2.nomePessoa)
        }
        append(", ")
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontSize = 12.sp,
                color = green900
            )
        ) {
            append(notificacaoDados2.textoNotificacao)
        }
        withStyle(
            style = SpanStyle(
                fontFamily = FontFamily(Font(R.font.inter_medium)),
                fontSize = 12.sp,
                color = gray500
            )
        ) {
            append(notificacaoDados2.horario)
        }
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier
                .background(white)
                .fillMaxSize()
        ) {
            HeaderWithBackground {
                IconButtonAction(
                    resource = R.drawable.icon_arrow_back,
                    sizeValue = 28,
                    modifier = Modifier.background(white)
                ) { navController.popBackStack() }

                Text(
                    text = stringResource(id = R.string.notification_header),
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 44.dp),
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    text = "Hoje",
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.lato_bold)),
                    color = green900
                )

                Spacer(modifier = Modifier.height(12.dp))
                    Column {
                        Row (
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Row (
                                    modifier = Modifier
                                        .width(280.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.profile),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .width(44.dp)
                                            .height(44.dp)
                                            .clip(RoundedCornerShape(22.dp)),
                                    )

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text(
                                        text = textNotificaiton
                                    )

                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Row {
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

                                    Spacer(modifier = Modifier.width(10.dp))

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
                            }

                            Spacer(modifier = Modifier.width(36.dp))

                            Image(
                                painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .width(56.dp)
                                    .height(78.dp)
                                    .clip(Shapes.small)
                            )
                        }

                        DividerCustom(20.dp, 20.dp)

                        Row (
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row (
                                modifier = Modifier
                                    .width(280.dp),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.profile),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .width(44.dp)
                                        .height(44.dp)
                                        .clip(RoundedCornerShape(22.dp)),
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = textNotificaiton2
                                )
                            }
                        }
                    }

            }
        }
    }


}