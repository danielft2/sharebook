package com.example.sharebook.book_management_feature.presentation.add_book.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.Logo
import com.example.sharebook.core.presentation.components.LogoType
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.Typography
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green300
import com.example.sharebook.core.presentation.ui.theme.green400
import com.example.sharebook.core.presentation.ui.theme.green800
import com.example.sharebook.core.utils.UiText

@Composable
fun AddBookHeader(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(20.dp)
    ) {
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

            Column(
                modifier = Modifier
                    .weight(2f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .padding(end = 44.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text(
                        text = UiText.StringResource(R.string.add_book_step_1_subtitle)
                            .asString(),
                        fontFamily = Lato,
                        fontWeight = FontWeight.Bold,
                        style = Typography.subtitle2,
                        color = gray500
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Box(
                    modifier = Modifier
                        .padding(end = 44.dp),
                    contentAlignment = Alignment.BottomStart
                ) {
                    Text(
                        text = UiText.StringResource(R.string.add_book_title).asString(),
                        fontFamily = Lato,
                        fontWeight = FontWeight.Bold,
                        style = Typography.subtitle1,
                        color = green800
                    )
                }
            }
        }
    }
    Column {
        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = green300,
            progress = 0.5f
        )
    }
}