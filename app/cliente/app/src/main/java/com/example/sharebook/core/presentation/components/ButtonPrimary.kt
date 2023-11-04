package com.example.sharebook.core.presentation.components

import android.widget.Button
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
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
    text: String,
    loading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = Shapes.medium,
        enabled = enabled && !loading,
        colors = ButtonDefaults.buttonColors(disabledBackgroundColor = green200),
        elevation = ButtonDefaults.elevation(defaultElevation = 1.dp, pressedElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(green600, shape = Shapes.medium)
            .height(48.dp)
            .padding(0.dp)
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
                color = white,
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