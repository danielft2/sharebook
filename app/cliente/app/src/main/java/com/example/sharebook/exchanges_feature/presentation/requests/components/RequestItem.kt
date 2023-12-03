package com.example.sharebook.exchanges_feature.presentation.requests.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.components.book.BookTag
import com.example.sharebook.core.presentation.components.divider.DividerCustom
import com.example.sharebook.core.presentation.components.ImageCustom
import com.example.sharebook.core.presentation.components.book.BookOwnerInformations
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.Functions
import com.example.sharebook.core.utils.skeleton
import com.example.sharebook.exchanges_feature.domain.model.RequestModel

@Composable
fun RequestItem(requestBook: RequestModel, onClick: () -> Unit) {
    val statusColors = Functions.getColorsByRequestStatus(status = requestBook.status)

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(210.dp)
        .background(white)
        .clickable { onClick() }
    ) {
        Row {
            ImageCustom(
                url = requestBook.bookImageURL,
                modifier = Modifier
                    .fillMaxHeight()
                    .width(115.dp)
            ) {
                Box(modifier = Modifier
                    .fillMaxHeight()
                    .width(115.dp)
                    .skeleton()
                )
            }

            Column(modifier = Modifier.padding(20.dp)) {
                Column {
                    Text(
                        text = "${requestBook.genders} - ${requestBook.edition}° Edição",
                        color = green600,
                        fontSize = 14.sp,
                        fontFamily = Lato,
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = requestBook.title,
                        color = green900,
                        fontSize = 15.sp,
                        fontFamily = Lato,
                        fontWeight = FontWeight.SemiBold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = requestBook.author,
                        color = gray500,
                        fontSize = 14.sp,
                        fontFamily = Lato,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                DividerCustom(spaceTop = 16.dp, spaceBottom = 16.dp)

                BookTag(
                    text = requestBook.status.tag,
                    background = statusColors.background,
                    colorText = statusColors.colorText
                )

                Spacer(modifier = Modifier.height(12.dp))

                BookOwnerInformations(
                    name = requestBook.owner,
                    secondaryText = "Dono(a) do Livro",
                    profileUrl = requestBook.ownerProfileURL,
                    falbackPhoto = requestBook.owner
                )
            }
        }
    }
}
