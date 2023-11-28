package com.example.sharebook.discovery_feature.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.Center
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
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
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.navigation.routes.authenticated.PrivateRoutes
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.green500
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.core.utils.Functions
import com.example.sharebook.discovery_feature.presentation.SearchBookViewModel

@Composable
fun Header (searchBookViewModel: SearchBookViewModel, navController: NavController) {
    //val userLogged = searchBookViewModel.uiState.user
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp)
            .paint(
                painterResource(id = R.drawable.dec_home),
                contentScale = ContentScale.FillWidth,
            )
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 76.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.sharebook), 
                contentDescription = null,
            )
            Row (
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { navController.navigate(PrivateRoutes.Notification.route) },
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

                Spacer(modifier = Modifier.width(8.dp))

                Box(modifier = Modifier
                    .size(42.dp)
                    .clip(RoundedCornerShape(100))
                    .background(white)
                    .wrapContentSize(Alignment.Center)
                ) {
                    Text(
                        text = Functions.userNameAvatar( "Daniel Almeida"),
                        color = green500,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = Lato
                    )
                }
            }
        }
    }
}