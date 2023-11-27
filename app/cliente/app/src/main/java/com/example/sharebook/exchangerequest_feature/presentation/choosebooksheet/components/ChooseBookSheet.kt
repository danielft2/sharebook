package com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.DividerCustom
import com.example.sharebook.core.presentation.components.statewrapper.StateWraper
import com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.ChooseBookViewModel
import com.example.sharebook.exchangerequest_feature.presentation.choosebooksheet.event.ChooseBookEvent
import com.example.sharebook.exchanges_feature.domain.model.MyBookModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseBookSheet(
    showBottomSheet: Boolean = false,
    maxHeight: Dp,
    onDismiss: () -> Unit,
    onSelected: (book: MyBookModel) -> Unit,
    chooseBookViewModel: ChooseBookViewModel = hiltViewModel()
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { onDismiss() },
            sheetState = sheetState,
            containerColor = white,
            modifier = Modifier
                .heightIn(min = maxHeight / 2, max = maxHeight),
            shape = RoundedCornerShape(12.dp),

        ) {
            Column(modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 50.dp)
                .height(maxHeight)
            ) {
                StateWraper(
                    onClickTryAgain = { chooseBookViewModel.getListMyBooks() },
                    isLoading = chooseBookViewModel.uiState.isLoading,
                    isError = !chooseBookViewModel.uiState.isError.isNullOrEmpty()
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(24.dp)) {
                        BasicTextField(modifier = Modifier
                            .background(color = gray200, shape = Shapes.medium)
                            .fillMaxWidth()
                            .border(width = 0.8.dp, color = gray200, shape = Shapes.medium)
                            .padding(16.dp),
                            value = chooseBookViewModel.uiState.search,
                            onValueChange = { chooseBookViewModel.event(ChooseBookEvent.SearchChange(it)) },
                            singleLine = true,
                            decorationBox = {text ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    text()
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_search),
                                        contentDescription = null,
                                        tint = gray500
                                    )
                                }
                            }
                        )

                        if (chooseBookViewModel.uiState.bookListFilter.isNotEmpty()) {
                            LazyColumn(modifier = Modifier
                                .nestedScroll(object : NestedScrollConnection {
                                    override fun onPostScroll(
                                        consumed: Offset,
                                        available: Offset,
                                        source: NestedScrollSource
                                    ) = available
                                }),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                itemsIndexed(chooseBookViewModel.uiState.bookListFilter) { index, it->
                                    BookItemRow(book = it) {
                                        onSelected(it)
                                        scope.launch {
                                            sheetState.hide()
                                            onDismiss()
                                        }
                                    }

                                    if (index != chooseBookViewModel.uiState.bookListFilter.lastIndex) {
                                        DividerCustom(spaceTop = 16.dp)
                                    }
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .offset(y = (-50).dp),
                                contentAlignment = Alignment.Center
                            ) { SearchNotFound() }
                        }
                    }
                }
            }
        }
    }
}