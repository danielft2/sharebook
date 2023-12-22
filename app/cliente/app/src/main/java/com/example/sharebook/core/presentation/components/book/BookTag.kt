package com.example.sharebook.core.presentation.components.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R

@Composable
fun BookTag(background: Color, colorText: Color, text: String, textSize: TextUnit? = 12.sp) {
    Box(
        modifier = Modifier
            .background(color = background, shape = RoundedCornerShape(4.dp))
            .padding(vertical = 4.dp, horizontal = 16.dp)
    ) {
        Text(
            text = text,
            fontFamily = FontFamily(Font(R.font.inter_semibold)),
            fontSize = textSize!!,
            color = colorText,
        )
    }
}
