package com.example.sharebook.core.presentation.components.input

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.*

@Composable
fun SwitchWithTextCustom(
    title: String,
    description: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    BoxWithConstraints(modifier = Modifier.fillMaxWidth()) {
        val width = maxWidth

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.widthIn(max = width - 100.dp)) {
                Text(
                    text = title,
                    fontFamily = Lato,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = green900
                )
                Text(
                    text = description,
                    fontFamily = Inter,
                    fontSize = 12.sp,
                    color = gray500,
                    style = TextStyle(lineHeight = 16.sp),
                    minLines = 2
                )
            }

            Switch(
                checked = checked,
                onCheckedChange = { onCheckedChange(it) },
                modifier = Modifier
                    .height(10.dp)
                    .padding(2.dp)
                ,
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = Color.Transparent,
                    uncheckedBorderColor = gray200,
                    uncheckedTrackColor = gray200,
                    checkedThumbColor = Color.Transparent,
                    checkedBorderColor = green600,
                    checkedTrackColor = green600
                ),
                thumbContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.switch_thumb),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            )
        }
    }

}