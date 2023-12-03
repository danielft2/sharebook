package com.example.sharebook.core.presentation.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.button.ButtonPrimary
import com.example.sharebook.core.presentation.components.button.types.ButtonType
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun SomethingWrong(onClickTryAgain: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth()
        .padding(16.dp)
        .offset(y = (-60).dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.ilus_notfound), 
                contentDescription = null,
                modifier = Modifier.size(180.dp)
            )
            
            Text(
                text = stringResource(id = R.string.something_wrong_title),
                fontFamily = Lato,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = green900
            )

            Text(
                text = stringResource(id = R.string.something_wrong_description),
                fontFamily = Inter,
                fontSize = 14.sp,
                color = gray500,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            ButtonPrimary(
                text = stringResource(id = R.string.something_wrong_try_again),
                buttonType = ButtonType.SECONDARY,
                modifier = Modifier.wrapContentWidth()
            ) { onClickTryAgain() }
        }
    }
}