package com.example.sharebook.core.presentation.components.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.components.avatar.AvatarUser
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun BookOwnerInformations(
    name: String,
    secondaryText: String,
    profileUrl: String,
    falbackPhoto: String

) {
    Row {
        AvatarUser(
            falback = falbackPhoto,
            profileUrl = profileUrl,
            modifier = Modifier
                .size(40.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(gray200)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Column (modifier = Modifier.align(Alignment.CenterVertically)) {
            Text(
                text = name,
                fontFamily = Inter,
                fontSize = 12.sp,
                color = green900,
                fontWeight = FontWeight.Medium
            )

            Text(
                text = secondaryText,
                fontFamily = Inter,
                fontSize = 12.sp,
                color = gray500,
                fontWeight = FontWeight.Medium
            )
        }
    }
}