package com.example.sharebook.core.presentation.components.input

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import com.example.sharebook.core.presentation.components.input.types.SelectItem
import com.example.sharebook.core.presentation.ui.theme.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SelectFieldCustom(
    label: String,
    value: SelectItem,
    errorMessage: String = "",
    itens: List<SelectItem> = listOf(),
    onChange: (value: SelectItem) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }
    val borderColor = if (errorMessage.isNotBlank()) red100 else Color.Transparent

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = label,
            color = green800,
            style = Typography.subtitle1
        )

        Spacer(modifier = Modifier.height(8.dp))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            BasicTextField(
                modifier = Modifier
                    .background(color = gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .border(width = 0.8.dp, color = borderColor, shape = Shapes.medium)
                    .padding(14.dp),
                value = value.label,
                onValueChange = {},
                readOnly = true,
                decorationBox = { innerTextField ->
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        innerTextField()
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = stringResource(id = R.string.dropdown_menu),
                            tint = Color.Black
                        )
                    }
                }
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(white)
            ) {
                itens.forEach { item ->
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item.label,
                                fontFamily = Inter,
                                fontSize = 14.sp,
                                color = green900
                            )
                        },
                        onClick = {
                            expanded = false
                            onChange(item)
                        }
                    )
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
