package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.green700
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.white

@Composable
fun HeaderDefault() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(green700)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Logo(type = LogoType.light)
            Row {
                IconButtonAction(
                    resource = R.drawable.icon_notification,
                    modifier = Modifier.background(white)
                ) {  }
                
                Spacer(modifier = Modifier.width(8.dp))
                
//                IconButtonAction(
//                    resource = R.drawable.icon_notification,
//                    modifier = Modifier.background(white)
//                ) {  }
            }
        }
    }
}