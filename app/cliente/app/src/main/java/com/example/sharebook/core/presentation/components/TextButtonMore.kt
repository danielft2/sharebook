package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green500


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TextButtonMore(text: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = text,
            color = gray500,
            maxLines = if (isExpanded) Int.MAX_VALUE else 3,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(lineHeight = 21.sp),
            fontSize = 14.sp
        )

        if (text.length > 150) {
            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false
            ) {
                TextButton(
                    onClick = { isExpanded = !isExpanded },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier
                        .defaultMinSize(minHeight = 1.dp)
                        .align(Alignment.Start)
                        .wrapContentWidth()
                ) {
                    Text(
                        text = if (isExpanded) "Mostrar menos" else "Mostrar mais",
                        fontFamily = Inter,
                        fontWeight = FontWeight.SemiBold,
                        style = TextStyle(letterSpacing = 0.sp),
                        color = green500,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}