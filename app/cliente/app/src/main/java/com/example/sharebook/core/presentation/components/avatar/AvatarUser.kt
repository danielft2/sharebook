package com.example.sharebook.core.presentation.components.avatar

import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.utils.Functions

@Composable
fun AvatarUser(
    falback: String, profileUrl: String,
    modifier: Modifier
) {
    if (profileUrl.isNotBlank()) {
        ImageCustom(
            url = profileUrl,
            modifier = Modifier.then(modifier)
        ) {}
    } else {
        Box(
            modifier = modifier.then(modifier),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = Functions.userNameAvatar(falback),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Inter,
                color = gray500
            )
        }
    }
}