package com.example.sharebook.welcome_feature.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.*
import com.example.sharebook.core.presentation.navigation.routes.unauthenticated.PublicRoutes
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.UiText

@Composable
fun Welcome(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF167872), background),
                    startY = 0f,
                    endY = 800f
                )
            )
            .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        )
        {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Logo(type = LogoType.light)
                Image(
                    painter = painterResource(id = R.drawable.ilus_welcome),
                    contentScale = ContentScale.FillHeight,
                    contentDescription = null,
                    modifier = Modifier.height(400.dp)
                )
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = UiText.StringResource(R.string.welcome_title).asString(),
                    color = green800,
                    fontSize = 24.sp,
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    lineHeight = 34.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.widthIn(max = 280.dp)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = UiText.StringResource(R.string.welcome_subtitle).asString(),
                    color = gray500,
                    fontSize = 14.sp,
                    fontFamily = Inter,
                    lineHeight = 21.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(48.dp))
                
                ButtonPrimary(text =  UiText.StringResource(R.string.welcome_button_login).asString()) {
                    navController.navigate(PublicRoutes.LoginScreen.route)
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButtonCustom(text = UiText.StringResource(R.string.welcome_button_register).asString()) {
                    navController.navigate(PublicRoutes.RegisterScreen.route)
                }
            }
        }
    }
}