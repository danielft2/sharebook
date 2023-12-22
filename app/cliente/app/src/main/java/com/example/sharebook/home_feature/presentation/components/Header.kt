package com.example.sharebook.home_feature.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.Functions
import com.example.sharebook.home_feature.presentation.HomeViewModel

@Composable
fun Header(homeViewModel: HomeViewModel, navController: NavController) {
    val userLogged = homeViewModel.uiState.user

    var isContextMenuVisible by rememberSaveable { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

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
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(100))
                    .background(white)
                    .wrapContentSize(Alignment.Center)
                    .indication(interactionSource, LocalIndication.current)
                    .pointerInput(true) {
                        detectTapGestures(
                            onPress = {
                                isContextMenuVisible = true
                                val press = PressInteraction.Press(it)
                                interactionSource.emit(press)
                                tryAwaitRelease()
                                interactionSource.emit(PressInteraction.Release(press))
                            }
                        )
                    }
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
                    onClick = { navController.navigate(PrivateRoutes.Notification.route) },
                    modifier = Modifier
                        .clip(RoundedCornerShape(100))
                        .background(white)
                        .padding(0.dp)
                        .size(40.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.icon_notification),
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp)
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

        DropdownMenu(
            modifier = Modifier.background(white),
            expanded = isContextMenuVisible,
            onDismissRequest = { isContextMenuVisible = false },
            offset = DpOffset(y = (-100).dp, x = 24.dp),

        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Sair do Aplicativo",
                        fontFamily = Lato,
                        fontWeight = FontWeight.Bold,
                        color = green900,
                        textAlign = TextAlign.Center
                    )
                },
                onClick = { homeViewModel.logout() },
            )
        }
    }
}
