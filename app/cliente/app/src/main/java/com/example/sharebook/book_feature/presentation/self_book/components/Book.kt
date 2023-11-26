package com.example.sharebook.book_feature.presentation.self_book.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.sharebook.book_feature.presentation.self_book.SelfBookViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green500
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.book_feature.presentation.external_book.components.BoolGaleryImage
import com.example.sharebook.core.presentation.components.*
import com.example.sharebook.core.utils.UiText

@Composable
fun SelfBook(
    viewModel: SelfBookViewModel = hiltViewModel(),
    navController: NavController
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column (modifier = Modifier
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
                    text = UiText.StringResource(R.string.book_details_header).asString(),
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(2f)
                )

                IconButtonAction(
                    resource = R.drawable.icon_edit,
                    sizeValue = 24,
                    modifier = Modifier
                ) {  }
            }

            Column(modifier = Modifier
                .padding(top = 0.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
                .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier
                    .weight(1f, fill = false)
                    .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(16.dp))

                    DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)

                    Column {
                        Text(
                            text = UiText.StringResource(R.string.book_datails_descreption_session).asString(),
                            fontFamily = FontFamily(Font(R.font.lato_bold)),
                            fontSize = 15.sp,
                            color = green900,
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        TextButtonMore(text = "bookDatails.descricao")
                    }

                    DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)
                    //BoolGaleryImage()

                    DividerCustom(spaceTop = 20.dp, spaceBottom = 20.dp)

//                    Column {
//                        Text(
//                            text = stringResource(id = R.string.book_datails_requests_session),
//                            fontFamily = FontFamily(Font(R.font.lato_bold)),
//                            fontSize = 15.sp,
//                            color = green900
//                        )
//
//                        Spacer(modifier = Modifier.height(12.dp))
//
//                        Row {
//                            Image(
//                                painter = painterResource(id = R.drawable.profile),
//                                contentDescription = null,
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .width(44.dp)
//                                    .height(44.dp)
//                                    .clip(RoundedCornerShape(22.dp))
//                            )
//
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            Column (
//                                modifier = Modifier
//                                    .align(Alignment.CenterVertically)
//                            ) {
//                                Text(
//                                    text = pedidoTroca.nomePessoa,
//                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                    fontSize = 12.sp,
//                                    color = green900
//                                )
//
//                                Text(
//                                    text = pedidoTroca.localPessoa,
//                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                    fontSize = 11.sp,
//                                    color = gray500
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(48.dp))
//
//                            Button(
//                                onClick = {},
//                                shape = Shapes.medium,
//                                modifier = Modifier
//                                    .height(36.dp)
//                                    .width(84.dp)
//                                    .padding(0.dp)
//                                    .align(Alignment.CenterVertically),
//                                colors = ButtonDefaults.buttonColors(green500)
//                            ){
//                                Text(
//                                    text = stringResource(id = R.string.book_datails_accept_button),
//                                    color = white,
//                                    fontFamily = Lato,
//                                    fontWeight = FontWeight.Bold,
//                                    fontSize = 12.sp,
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            Button (
//                                onClick = {},
//                                modifier = Modifier
//                                    .align(Alignment.CenterVertically)
//                                    .height(36.dp)
//                                    .width(84.dp),
//                                colors = ButtonDefaults.buttonColors(gray200)
//                            ) {
//                                Text(
//                                    text = stringResource(id = R.string.book_datails_refuse_button),
//                                    fontFamily = Lato,
//                                    fontWeight = FontWeight.Bold,
//                                    fontSize = 12.sp,
//                                    color = green900
//                                )
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(20.dp))
//
//                        Row {
//                            Image(
//                                painter = painterResource(id = R.drawable.profile),
//                                contentDescription = null,
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .width(44.dp)
//                                    .height(44.dp)
//                                    .clip(RoundedCornerShape(22.dp))
//                            )
//
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            Column (
//                                modifier = Modifier
//                                    .align(Alignment.CenterVertically)
//                            ) {
//                                Text(
//                                    text = pedidoTroca.nomePessoa,
//                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                    fontSize = 12.sp,
//                                    color = green900
//                                )
//
//                                Text(
//                                    text = pedidoTroca.localPessoa,
//                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                    fontSize = 11.sp,
//                                    color = gray500
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(48.dp))
//
//                            Button(
//                                onClick = { },
//                                shape = Shapes.medium,
//                                modifier = Modifier
//                                    .height(36.dp)
//                                    .width(84.dp)
//                                    .padding(0.dp)
//                                    .align(Alignment.CenterVertically),
//                                colors = ButtonDefaults.buttonColors(green500)
//                            ){
//                                Text(
//                                    text = stringResource(id = R.string.book_datails_accept_button),
//                                    color = white,
//                                    fontFamily = Lato,
//                                    fontWeight = FontWeight.Bold,
//                                    fontSize = 12.sp,
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            Button (
//                                onClick = { },
//                                modifier = Modifier
//                                    .align(Alignment.CenterVertically)
//                                    .height(36.dp)
//                                    .width(84.dp),
//                                colors = ButtonDefaults.buttonColors(gray200)
//                            ) {
//                                Text(
//                                    text = stringResource(id = R.string.book_datails_refuse_button),
//                                    fontFamily = Lato,
//                                    fontWeight = FontWeight.Bold,
//                                    fontSize = 12.sp,
//                                    color = green900
//                                )
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(20.dp))
//
//                        Row {
//                            Image(
//                                painter = painterResource(id = R.drawable.profile),
//                                contentDescription = null,
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .width(44.dp)
//                                    .height(44.dp)
//                                    .clip(RoundedCornerShape(22.dp))
//                            )
//
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            Column (
//                                modifier = Modifier
//                                    .align(Alignment.CenterVertically)
//                            ) {
//                                Text(
//                                    text = pedidoTroca.nomePessoa,
//                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                    fontSize = 12.sp,
//                                    color = green900
//                                )
//
//                                Text(
//                                    text = pedidoTroca.localPessoa,
//                                    fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                    fontSize = 11.sp,
//                                    color = gray500
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(48.dp))
//
//                            Button(
//                                onClick = { },
//                                shape = Shapes.medium,
//                                modifier = Modifier
//                                    .height(36.dp)
//                                    .width(84.dp)
//                                    .padding(0.dp)
//                                    .align(Alignment.CenterVertically),
//                                colors = ButtonDefaults.buttonColors(green500)
//                            ){
//                                Text(
//                                    text = stringResource(id = R.string.book_datails_accept_button),
//                                    color = white,
//                                    fontFamily = Lato,
//                                    fontWeight = FontWeight.Bold,
//                                    fontSize = 12.sp,
//                                )
//                            }
//
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            Button (
//                                onClick = { },
//                                modifier = Modifier
//                                    .align(Alignment.CenterVertically)
//                                    .height(36.dp)
//                                    .width(84.dp),
//                                colors = ButtonDefaults.buttonColors(gray200)
//                            ) {
//                                Text(
//                                    text = stringResource(id = R.string.book_datails_refuse_button),
//                                    fontFamily = Lato,
//                                    fontWeight = FontWeight.Bold,
//                                    fontSize = 12.sp,
//                                    color = green900
//                                )
//                            }
//                        }
//                    }
                }
            }
        }

    }
}