package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    loading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled && !loading,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = green600,
            disabledBackgroundColor = green200,
            contentColor = white
        ),
        shape = Shapes.medium,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 1.dp,
            pressedElevation = 2.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(0.dp)
            .then(modifier)
    ){
        if (loading) {
            CircularProgressIndicator(
                color = gray100,
                strokeWidth = 2.5.dp,
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            )
        } else {
            Text(
                text = text,
                fontFamily = Lato,
                fontWeight = FontWeight.Bold,
                style = TextStyle(letterSpacing = 0.sp),
                modifier = Modifier
                    .padding(0.dp)
                    .background(Color.Transparent)
            )
        }
    }
}
