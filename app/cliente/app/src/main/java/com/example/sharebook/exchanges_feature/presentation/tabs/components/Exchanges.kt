package com.example.sharebook.exchanges_feature.presentation.tabs.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.sharebook.core.presentation.components.HeaderDefault
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.exchanges_feature.presentation.enteredprocess.components.EnteredProcess
import com.example.sharebook.exchanges_feature.presentation.mybooks.components.MyBooks
import com.example.sharebook.exchanges_feature.presentation.tabs.tabsList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Exchanges(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize()) {
        var selectedTabIndex by remember { mutableStateOf(0) }
        val pagerState = rememberPagerState{2}

        LaunchedEffect(key1 = selectedTabIndex) {
            pagerState.animateScrollToPage(selectedTabIndex)
        }

        LaunchedEffect(pagerState.currentPage) {
            selectedTabIndex = pagerState.currentPage
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .background(background)
        ) {
            HeaderDefault()
            TabRow(selectedTabIndex = selectedTabIndex, backgroundColor = green700) {
                tabsList.forEachIndexed { index, tabItem ->
                    val tabSelected = index == selectedTabIndex
                    Tab(
                        selected = tabSelected,
                        onClick = { selectedTabIndex = index },
                        text = {
                            Row {
                                Text(
                                    text = tabItem.title,
                                    color = if (tabSelected) white else gray300,
                                    fontFamily = Lato,
                                    fontWeight = FontWeight.Bold,
                                    style = TextStyle(letterSpacing = 0.sp)
                                )
                                
                                Spacer(modifier = Modifier.width(8.dp))
                                
                                Box(modifier = Modifier
                                    .width(20.dp)
                                    .height(20.dp)
                                    .clip(RoundedCornerShape(100))
                                    .background(if (tabSelected) green400 else gray300),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = tabItem.badgeValue.toString(),
                                        color = if (tabSelected) white else gray800,
                                        fontFamily = Lato,
                                        fontWeight = FontWeight.Bold,
                                    )
                                }
                            }

                        }
                    )
                }
            }

            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(top = 16.dp)
            ) {
                when(it) {
                    0 -> { MyBooks { route -> navController.navigate(route) } }
                }
            }
        }
    }
}