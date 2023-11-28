package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.green600

@Composable
fun DividerDecorator() {
    Column {
        Spacer(modifier = Modifier.height(24.dp))

        Box(modifier = Modifier.fillMaxWidth()) {
            Box(modifier = Modifier.offset(y = 24.dp)) {
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(gray200))
            }

            Box(modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(100))
                .background(green600)
                .align(Alignment.Center),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_top_bottom),
                    contentDescription = null,
                    Modifier.size(24.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}