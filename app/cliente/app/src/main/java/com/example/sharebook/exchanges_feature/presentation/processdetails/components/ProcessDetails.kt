package com.example.sharebook.exchanges_feature.presentation.processdetails.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.components.*
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.exchanges_feature.data.local.BookProcessMock
import com.example.sharebook.exchanges_feature.presentation.processdetails.tabs.listTabs
import com.example.sharebook.exchanges_feature.presentation.tabs.tabsList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ProcessDetails(navController: NavHostController) {
    val book = BookProcessMock()
    var selectedTabIndex by remember { mutableStateOf(0) }
    val pagerState = rememberPagerState()

    LaunchedEffect(key1 = selectedTabIndex) {
        pagerState.animateScrollToPage(selectedTabIndex)
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column (modifier = Modifier
            .background(white)
            .fillMaxSize()
        ) {
            HeaderWithBackground {
                IconButtonAction(
                    resource = R.drawable.icon_arrow_back,
                    sizeValue = 28,
                    modifier = Modifier.background(white)
                ) { navController.popBackStack() }

                Text(
                    text = "Detalhes do Processo",
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    color = white,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .weight(2f)
                        .padding(end = 44.dp),
                )
            }

            Column(modifier = Modifier
                .padding(top = 0.dp, start = 16.dp, bottom = 16.dp, end = 16.dp)
                .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                BookInformations()
                Spacer(modifier = Modifier.height(20.dp))
                
                TabRow(selectedTabIndex, backgroundColor = white) {
                    listTabs.forEachIndexed { index, tabScreen ->
                        val tabSelected = index == selectedTabIndex

                        Tab(
                            selected = tabSelected,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        painter = painterResource(id = tabScreen.icon),
                                        contentDescription = null,
                                        tint = if (tabSelected) green600 else gray500
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = tabScreen.name,
                                        color = if (tabSelected) green600 else gray500,
                                        style = TextStyle(letterSpacing = 0.sp),
                                        fontFamily = Inter,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                        )
                    }
                }

                Column(modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f, fill = false)
                    .padding(top = 16.dp)
                    .verticalScroll(rememberScrollState())
                ) {
                    when(selectedTabIndex) {
                        0 -> { BookOtherInformations(book = book) }
                    }
                }
            }
        }
    }
}