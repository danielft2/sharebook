package com.example.sharebook.core.presentation.components.book

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.utils.skeleton

@Composable
fun BookSummarySkeleton() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .height(150.dp)
            .width(110.dp)
            .clip(shape = Shapes.small)
            .background(gray200)
            .skeleton()
        ) {}

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Box(
                modifier = Modifier
                    .width(150.dp)
                    .height(12.dp)
                    .clip(shape = Shapes.small)
                    .background(gray200)
                    .skeleton()
            )
            Box(
                modifier = Modifier
                    .width(200.dp)
                    .height(12.dp)
                    .clip(shape = Shapes.small)
                    .background(gray200)
                    .skeleton()
            )
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(12.dp)
                    .clip(shape = Shapes.small)
                    .background(gray200)
                    .skeleton()
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(20.dp)
                        .clip(shape = Shapes.small)
                        .background(gray200)
                        .skeleton()
                )
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(20.dp)
                        .clip(shape = Shapes.small)
                        .background(gray200)
                        .skeleton()
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(100))
                    .background(gray200)
                    .skeleton()
                )

                Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                    Box(
                        modifier = Modifier
                            .width(120.dp)
                            .height(12.dp)
                            .clip(shape = Shapes.small)
                            .background(gray200)
                            .skeleton()
                    )

                    Box(
                        modifier = Modifier
                            .width(100.dp)
                            .height(12.dp)
                            .clip(shape = Shapes.small)
                            .background(gray200)
                            .skeleton()
                    )
                }
            }
        }
    }
}