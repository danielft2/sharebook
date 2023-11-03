package com.example.sharebook.home_feature.presentation.components

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun GenderRender(title: String) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = gray200),
        elevation = ButtonDefaults.elevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
        ) {
        Text(
            text = title,
            style = TextStyle(letterSpacing = 0.sp),
            color = green900,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Lato,
            fontSize = 12.sp
        )
    }
}
