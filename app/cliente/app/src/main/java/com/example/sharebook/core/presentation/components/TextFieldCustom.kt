package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun TextFieldCustom(
    label: String,
    value: String,
    errorMessage: String = "",
    onChange: (value: String) -> Unit,
    enable: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,

) {
    Column {
        val borderColor = if (errorMessage.isNotBlank()) red100 else Color.Transparent
        val backgroundColor = if (enable) gray200 else gray400

        Text(
            text = label,
            color = green800,
            style = Typography.subtitle1
        )

        Spacer(modifier = Modifier.height(4.dp))

        BasicTextField(
            value = value,
            onValueChange = { onChange(it) },
            singleLine = true,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            enabled = enable,
            modifier = Modifier
                .background(color = backgroundColor, shape = Shapes.medium)
                .fillMaxWidth()
                .border(width = 0.8.dp, color = borderColor, shape = Shapes.medium)
                .padding(16.dp)

        )

        if (errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = red400,
                style = Typography.subtitle2
            )
        }
    }
}
