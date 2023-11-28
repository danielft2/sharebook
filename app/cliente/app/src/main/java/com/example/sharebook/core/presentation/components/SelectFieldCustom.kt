package com.example.sharebook.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.presentation.ui.theme.Typography
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.gray400
import com.example.sharebook.core.presentation.ui.theme.red100
import com.example.sharebook.core.presentation.ui.theme.red400
import com.example.sharebook.core.presentation.ui.theme.green800

@Composable
fun SelectFieldCustom(
    label: String,
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    errorMessage: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    var expanded by remember { mutableStateOf(false) }

    val borderColor = if (errorMessage.isNotBlank()) red100 else Color.Transparent
    val backgroundColor = gray200

    Column {
        Text(
            text = label,
            color = green800,
            style = Typography.subtitle1
        )

        Spacer(modifier = Modifier.height(4.dp))

        Box(
            modifier = Modifier
                .background(color = backgroundColor, shape = Shapes.medium  )
                .fillMaxWidth()
                .border(width = 0.8.dp, color = borderColor, shape = Shapes.medium)
                .padding(horizontal = 16.dp)
                .clickable {
                    expanded = !expanded
                },
            contentAlignment = Alignment.CenterEnd
        ) {
            BasicTextField(
                value = selectedItem,
                onValueChange = { onItemSelected(it) },
                singleLine = true,
                visualTransformation = visualTransformation,
                enabled = false,
                modifier = Modifier
                    .fillMaxWidth()
            )

            IconButton(
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = stringResource(id = R.string.dropdown_menu),
                    tint = Color.Black
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = {
                    expanded = false
                },
                modifier = Modifier
                    .background(color = backgroundColor, shape = Shapes.medium)
                    .border(width = 0.8.dp, color = borderColor, shape = Shapes.medium)
            ) {
                items.forEach { item ->
                    DropdownMenuItem(
                        onClick = {
                            onItemSelected(item)
                            expanded = false
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                    ) {
                        Text(text = item)
                    }
                }
            }
        }


        if (errorMessage.isNotBlank()) {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = errorMessage,
                color = red400,
                style = Typography.subtitle2
            )
        }
    }
}





