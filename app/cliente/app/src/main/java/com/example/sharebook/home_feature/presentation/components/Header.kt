package com.example.sharebook.home_feature.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.Functions
import com.example.sharebook.home_feature.presentation.HomeViewModel

@Composable
fun Header(homeViewModel: HomeViewModel) {
    val userLogged = homeViewModel.uiState.user

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .clip(
                RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 16.dp,
                    bottomEnd = 16.dp
                )
            )
            .paint(
                painterResource(id = R.drawable.dec_home),
                contentScale = ContentScale.Crop
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(100))
                    .background(white)
                    .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = Functions.userNameAvatar(userLogged?.name ?: ""),
                        color = green500,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Lato
                    )
                }

                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .background(white)
                        .padding(0.dp)
                        .size(42.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_notification),
                        contentDescription = null,
                        modifier = Modifier
                            .size(22.dp)
                            .padding(end = 1.dp, bottom = 1.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Column {
                Text(
                    text = "Bem vindo(a) de volta.",
                    fontFamily = Lato,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp,
                    color = gray200
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "Olá, ${Functions.userNameAccount(userLogged?.name ?: "")}",
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = white
                )
            }
        }
    }
}
