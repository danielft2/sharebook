package com.example.sharebook.exchangerequest_feature.presentation.exchangerequest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.green600
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun AcceptTerms(
    accept: Boolean,
    onChange: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Checkbox(
            modifier = Modifier
                .background(gray200)
                .size(20.dp)
                .graphicsLayer(),
            checked = accept,
            onCheckedChange = { onChange() },
            colors = CheckboxDefaults.colors(
                uncheckedColor = gray200,
                checkedColor = green600
            )
        )
        Text(
            text = stringResource(id = R.string.exchange_request_confirm_checkbox),
            fontFamily = Inter,
            fontSize = 13.sp,
            style = TextStyle(lineHeight = (13 * 1.5).sp),
            color = green900
        )
    }
}