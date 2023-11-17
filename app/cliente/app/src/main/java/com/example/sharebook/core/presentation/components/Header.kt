package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.green400

@Composable
fun Header(onClick: () -> Unit) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onClick,
                modifier = Modifier
                    .padding(2.dp)
                    .background(Color.Transparent)
                    .border(width = 1.6.dp, green400, RoundedCornerShape(100))
                    .width(44.dp)
                    .height(44.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.icon_arrow_back),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(34.dp)
                        .height(34.dp)
                        .padding(end = 2.dp)
                )
            }
            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(end = 44.dp),
                contentAlignment = Alignment.Center
            ) {
                Logo(type = LogoType.green)
            }
        }
    }

}
