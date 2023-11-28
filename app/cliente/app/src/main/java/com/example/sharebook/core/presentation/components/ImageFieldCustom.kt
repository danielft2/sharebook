package com.example.sharebook.core.presentation.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.sharebook.core.presentation.ui.theme.Typography
import com.example.sharebook.core.presentation.ui.theme.red100
import com.example.sharebook.core.presentation.ui.theme.red400

@Composable
fun ImageFieldCustom(
    label: String,
    description: String,
    errorMessage: String = "",
    onImageSelected: (value: String) -> Unit
) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val getContent = rememberLauncher { uri -> selectedImageUri = uri }
    val borderColor = if (errorMessage.isNotBlank()) red100 else Color.Transparent
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 8.dp)
    ) {
        Text(
            text = label,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 4.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = {
                    getContent.launch("image/*")
                },
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .border(color = borderColor, width = 0.8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    if (selectedImageUri != null) {
                        Image(
                            painter = rememberAsyncImagePainter(selectedImageUri),
                            contentDescription = "Imagem Selecionada",
                            modifier = Modifier
                                .size(64.dp)
                                .padding(0.dp),
                        )
                    }
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Adicionar Imagem"
                    )
                    Text(text = description)
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
        if (selectedImageUri != null) {
            onImageSelected(selectedImageUri.toString())
        }
    }
}

@Composable
private fun rememberLauncher(onImageSelected: (Uri?) -> Unit): ActivityResultLauncher<String> {
    return rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> onImageSelected(uri) }
    )
}