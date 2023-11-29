package com.example.sharebook.exchanges_feature.presentation.request_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.sharebook.R
import com.example.sharebook.core.domain.enum.BookRequestStatus
import com.example.sharebook.core.presentation.components.button.ButtonPrimary
import com.example.sharebook.core.presentation.components.button.types.ButtonType

@Composable
fun ButtonConditional(
    bookRequestStatus: BookRequestStatus,
    isRequestFromUserLogged: Boolean,
    onChangeStatusRequest: (status: BookRequestStatus) -> Unit,
    onNavigateBack: () -> Unit,
    isLoading: Boolean = false
) {
    when (bookRequestStatus) {
        BookRequestStatus.ACCEPTED -> {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                ButtonPrimary(
                    text = stringResource(id = R.string.request_details_button_cancel_exchange),
                    buttonType = ButtonType.SECONDARY,
                    modifier = Modifier.weight(1f),
                    loading = isLoading
                ) { onChangeStatusRequest(BookRequestStatus.CANCEL) }
                ButtonPrimary(
                    text = stringResource(id = R.string.request_details_button_finalize_exchange),
                    modifier = Modifier.weight(1f),
                    loading = isLoading
                ) { onChangeStatusRequest(BookRequestStatus.FINALIZE) }
            }
        }
        BookRequestStatus.SEND -> {
            if (isRequestFromUserLogged) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    ButtonPrimary(
                        text = stringResource(id = R.string.request_details_button_reject_request),
                        buttonType = ButtonType.SECONDARY,
                        modifier = Modifier.weight(1f),
                        loading = isLoading
                    ) { onChangeStatusRequest(BookRequestStatus.CANCEL) }
                    ButtonPrimary(
                        text = stringResource(id = R.string.request_details_button_accepet_request),
                        modifier = Modifier.weight(1f),
                        loading = isLoading
                    ) { onChangeStatusRequest(BookRequestStatus.FINALIZE) }
                }
            } else {
                ButtonPrimary(
                    text = stringResource(id = R.string.request_details_button_cancel_request),
                    buttonType = ButtonType.SECONDARY,
                    loading = isLoading
                ) { onChangeStatusRequest(BookRequestStatus.FINALIZE) }
            }
        }
        BookRequestStatus.FINALIZE, BookRequestStatus.CANCEL -> {
            ButtonPrimary(
                text = stringResource(id = R.string.request_details_button_back),
                buttonType = ButtonType.SECONDARY,
                loading = isLoading
            ) { onNavigateBack() }
        }
    }
}

