package com.example.sharebook.core.presentation.components.input

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.R
import com.example.sharebook.core.utils.Functions
import java.io.File

@Composable
fun FileInputCustom(
    label: String,
    value: String,
    errorMessage: String = "",
    onChange: (value: File) -> Unit,
) {
    val context = LocalContext.current

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            if (uri != null) {
                val file = Functions.createTmpFileFromUri(context, uri, "capa")
                onChange(file!!)
            }
        }
    )

    Column {
        val borderColor = if (errorMessage.isNotBlank()) red100 else Color.Transparent

        Text(
            text = label,
            color = green800,
            style = Typography.subtitle1
        )

        Spacer(modifier = Modifier.height(8.dp))

        BasicTextField(
            modifier = Modifier
                .background(color = gray200, shape = Shapes.medium)
                .fillMaxWidth()
                .border(width = 0.8.dp, color = borderColor, shape = Shapes.medium)
                .padding(12.dp),
            value = value,
            onValueChange = { },
            singleLine = true,
            readOnly = true,
            decorationBox = { innerTextField ->
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(shape = Shapes.medium)
                                .background(white)
                                .clickable {
                                    singlePhotoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_file_add),
                                contentDescription = null,
                                tint = green600
                            )
                        }

                        innerTextField()
                    } 
                }
            }
        )

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
