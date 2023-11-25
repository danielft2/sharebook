package com.example.sharebook.core.presentation.components.button

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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.components.button.types.ButtonType
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun ButtonPrimary(
    modifier: Modifier = Modifier,
    text: String,
    loading: Boolean = false,
    enabled: Boolean = true,
    buttonType: ButtonType = ButtonType.PRIMARY,
    onClick: () -> Unit
) {
    val style = getStyleByType(buttonType)

    Button(
        onClick = onClick,
        enabled = enabled && !loading,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = style.backgroundColor,
            contentColor = style.contentColor,
            disabledBackgroundColor = style.disabledBackgroundColor
        ),
        shape = Shapes.medium,
        elevation = ButtonDefaults.elevation(
            defaultElevation = style.defaultElevation,
            pressedElevation = style.pressedElevation
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

@Composable
fun getStyleByType(buttonType: ButtonType): ButtonTypeStyle {
    return if (buttonType == ButtonType.PRIMARY) {
        ButtonTypeStyle(
            backgroundColor = green600,
            contentColor = white,
            disabledBackgroundColor = green200,
            defaultElevation = 1.dp,
            pressedElevation = 2.dp
        )
    } else {
        ButtonTypeStyle(
            backgroundColor = gray200,
            contentColor = gray800,
            disabledBackgroundColor = green200,
            defaultElevation = 0.dp,
            pressedElevation = 2.dp
        )
    }
}

data class ButtonTypeStyle(
    val backgroundColor: Color,
    val contentColor: Color,
    val disabledBackgroundColor: Color,
    val defaultElevation: Dp,
    val pressedElevation: Dp
)

