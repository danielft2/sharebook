package com.example.sharebook.book_feature.presentation.form_book

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.Functions
import java.io.File

@Composable
fun ImageFileInput(
    onChange: (file: File, isAdd: Boolean) -> Unit)
{
    val context = LocalContext.current

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedFile by remember { mutableStateOf<File?>(null) }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
            println(uri)
            if (uri != null) {
                val file = Functions.createTmpFileFromUri(context, uri, "Additional")
                selectedFile = file
                onChange(file!!, true)
            }
        }
    )

    Column {
        if (selectedImageUri === null) {
            Box(modifier = Modifier
                .height(150.dp)
                .width(100.dp)
                .clip(shape = Shapes.small)
                .background(gray200)
                .clickable {
                    singlePhotoPickerLauncher.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                contentAlignment = Alignment.Center
            ) {
                Box(modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(100))
                    .background(white),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.icon_file_add),
                        contentDescription = null,
                        tint = green600
                    )
                }
            }
        } else {
            Box(modifier = Modifier
                .height(150.dp)
                .width(100.dp)
                .clip(Shapes.medium)
            ) {
                Box(modifier = Modifier.offset(30.dp, 50 .dp).zIndex(3f)) {
                    Box(modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(100))
                        .background(red400)
                        .clickable {
                            onChange(selectedFile!!, false)
                            selectedImageUri = null
                        },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(20.dp),
                            painter = painterResource(id = R.drawable.icon_close),
                            contentDescription = null
                        )
                    }
                }

                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .zIndex(2f)
                )

                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = selectedImageUri,
                    contentDescription = null,
                    contentScale = ContentScale.FillHeight
                )
            }
        }
    }
}