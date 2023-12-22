package com.example.sharebook.exchanges_feature.presentation.request_details.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun UserExternalInformations(
    phone: String,
    location: String
) {
    val context = LocalContext.current
    val dialIntent = Intent(Intent.ACTION_DIAL)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(id = R.string.request_details_field_location),
                fontFamily = Lato,
                fontWeight = FontWeight.Bold,
                color = green900,
                fontSize = 14.sp
            )

            Text(
                text = location,
                fontFamily = Inter,
                color = gray500,
                fontSize = 13.sp
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(
                text = stringResource(id = R.string.request_details_field_phone),
                fontFamily = Lato,
                fontWeight = FontWeight.Bold,
                color = green900,
                fontSize = 14.sp
            )

            Text(
                text = phone,
                fontFamily = Inter,
                color = gray500,
                fontSize = 13.sp,
                modifier = Modifier.clickable {
                    dialIntent.data = Uri.parse("tel:$phone")
                }
            )
        }
    }
}