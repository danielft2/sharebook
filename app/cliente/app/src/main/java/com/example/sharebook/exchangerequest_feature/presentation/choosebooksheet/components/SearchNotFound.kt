package com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.Inter
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green900

@Composable
fun SearchNotFound() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Image(
            modifier = Modifier.width(200.dp),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(id = R.drawable.ilus_notfound),
            contentDescription = null
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.choose_book_sheet_search_not_found_title),
                fontSize = 16.sp,
                fontFamily = Lato,
                fontWeight = FontWeight.Bold,
                color = green900,
                textAlign = TextAlign.Center
            )
            Text(
                text = stringResource(id = R.string.choose_book_sheet_search_not_found_description),
                fontSize = 13.sp,
                fontFamily = Inter,
                color = gray500,
                textAlign = TextAlign.Center
            )
        }
    }

}