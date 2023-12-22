package com.example.sharebook.discovery_feature.presentation.components

import android.widget.ToggleButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.core.presentation.ui.theme.Lato
import com.example.sharebook.core.presentation.ui.theme.Shapes
import com.example.sharebook.core.presentation.ui.theme.background
import com.example.sharebook.core.presentation.ui.theme.gray200
import com.example.sharebook.core.presentation.ui.theme.gray500
import com.example.sharebook.core.presentation.ui.theme.green100
import com.example.sharebook.core.presentation.ui.theme.green500
import com.example.sharebook.core.presentation.ui.theme.green600
import com.example.sharebook.core.presentation.ui.theme.green900
import com.example.sharebook.core.presentation.ui.theme.white
import com.example.sharebook.discovery_feature.presentation.SearchBookViewModel

@Composable
fun FilterDialog(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)

        ) {
            content()
        }
    }
}
@Composable
fun AlertDialog(
    onDismiss: () -> Unit,
    content: @Composable () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .padding(10.dp)

        ) {
            content()
        }
    }
}
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Discovery(
    viewModel: SearchBookViewModel = hiltViewModel(),
    navController: NavController
) {
    var text = remember { mutableStateOf("") }
    var isFilterDialogVisible = remember { mutableStateOf(false) }
    var isAlertDialogVisible = remember { mutableStateOf(false) }
    var applyLocalizationFilter = remember { mutableStateOf(false) }
    var applyUserSendFilter = remember { mutableStateOf(false) }
    var applyLocalizationInAlert = remember { mutableStateOf(false) }
    var applyUserSendInAlert = remember { mutableStateOf(false) }
    var applyUserReceiveFilter = remember { mutableStateOf(false) }
    var pesquisouLivro = remember { mutableStateOf(false) }


    val filterIcon = ImageVector.vectorResource(id = R.drawable.filter_icon)
    val notificationIcon = ImageVector.vectorResource(id = R.drawable.icon_notification)

    Surface(modifier = Modifier.fillMaxSize()) {
//        Column(modifier = Modifier
//            .fillMaxSize()
//            .background(background))
//        {
//            Header(
//                searchBookViewModel = viewModel,
//                navController
//            )
//            LazyColumn(
//            ) {
//                items(count = 1) {
//                    Column(
//                    ) {
//                        Row (
//                            modifier = Modifier
//                                .padding(20.dp, bottom = 20.dp, top = 32.dp)
//                        ) {
//                            TextField(
//                                value = text.value,
//                                shape = Shapes.medium,
//                                onValueChange = { newText -> text.value = newText },
//                                keyboardOptions = KeyboardOptions.Default.copy(
//                                    imeAction = ImeAction.Search
//                                ),
//                                keyboardActions = KeyboardActions(
//                                    onSearch = {
//                                        pesquisouLivro.value = true
//                                    }
//                                ),
//                                colors = TextFieldDefaults.textFieldColors(
//                                    backgroundColor = gray200,
//                                    unfocusedIndicatorColor = Color.Transparent,
//                                    focusedIndicatorColor = Color.Transparent,
//                                    textColor = green900
//                                ),
//                                placeholder = {Text(
//                                    text = "Busque por um livro",
//                                    color = gray500,
//                                    fontFamily = FontFamily(Font(R.font.inter_medium))
//                                )},
//                                leadingIcon = {
//                                    Icon(
//                                        imageVector = Icons.Default.Search,
//                                        contentDescription = null,
//                                        modifier = Modifier.size(24.dp),
//                                        tint = Color.Gray
//                                    )
//                                },
//                                modifier = Modifier
//                                    .height(48.dp)
//                            )
//
//                            Spacer(modifier = Modifier.width(8.dp))
//
//                            Button(
//                                onClick = { isFilterDialogVisible.value = true },
//                                colors = ButtonDefaults.buttonColors(green600),
//                                modifier = Modifier
//                                    .height(46.dp)
//                            ) {
//                                Icon(
//                                    imageVector = filterIcon,
//                                    contentDescription = null,
//                                    modifier = Modifier.size(24.dp),
//                                    tint = Color.White
//                                )
//                            }
//
//                        }
//                        /* *****Modal de filtros***** */
//                        if (isFilterDialogVisible.value) {
//                            FilterDialog(onDismiss = { isFilterDialogVisible.value = false }) {
//                                Column (
//                                    modifier = Modifier
//                                        .background(white, shape = Shapes.medium)
//                                        .padding(20.dp, 24.dp)
//
//                                ) {
//                                    Row (
//                                        modifier = Modifier
//
//                                    ) {
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.Top)
//                                                .width(290.dp)
//                                        ) {
//                                            Text(
//                                                text = stringResource(R.string.search_filter_localizacao_title),
//                                                fontSize = 14.sp,
//                                                fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                                color = green900
//                                            )
//                                            Spacer(modifier = Modifier.height(4.dp))
//                                            Text(
//                                                text = stringResource(R.string.search_filter_localizacao_description),
//                                                fontSize = 12.sp,
//                                                fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                                color = gray500
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(28.dp))
//                                        Box (
//                                        ) {
//                                            Switch(
//                                                checked = applyLocalizationFilter.value,
//                                                onCheckedChange = {applyLocalizationFilter.value = it },
//                                                modifier = Modifier
//                                                    .size(12.dp)
//                                            )
//                                        }
//
//                                    }
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//                                    Box(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(2.dp)
//                                            .background(gray200)
//                                    ) {}
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Row {
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.Top)
//                                                .width(290.dp)
//                                        ){
//                                            Text(
//                                                text = stringResource(R.string.search_filter_usuario_envia_title),
//                                                fontSize = 14.sp,
//                                                fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                                color = green900
//                                            )
//                                            Spacer(modifier = Modifier.height(4.dp))
//                                            Text(
//                                                text = stringResource(R.string.search_filter_usuario_envia_description),
//                                                fontSize = 12.sp,
//                                                fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                                color = gray500
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(28.dp))
//                                        Box{
//                                            Switch(
//                                                checked = applyUserSendFilter.value,
//                                                onCheckedChange = {applyUserSendFilter.value = it } ,
//                                                modifier = Modifier
//                                                    .size(12.dp)
//                                            )
//                                        }
//
//                                    }
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//                                    Box(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(2.dp)
//                                            .background(gray200)
//                                    ) {}
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Row {
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.Top)
//                                                .width(290.dp)
//                                        ) {
//                                            Text(
//                                                text = stringResource(R.string.search_filter_usuario_recebe_title),
//                                                fontSize = 14.sp,
//                                                fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                                color = green900
//                                            )
//                                            Spacer(modifier = Modifier.height(4.dp))
//                                            Text(
//                                                text = stringResource(R.string.search_filter_usuario_recebe_description),
//                                                fontSize = 12.sp,
//                                                fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                                color = gray500
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(28.dp))
//                                        Box {
//                                            Switch(
//                                                checked = applyUserReceiveFilter.value,
//                                                onCheckedChange = {applyUserReceiveFilter.value = it },
//                                                modifier = Modifier
//                                                    .size(12.dp)
//                                            )
//                                        }
//                                    }
//
//                                }
//                            }
//                        }
//
//                        /* *****Modal de alert***** */
//                        if (isAlertDialogVisible.value) {
//                            AlertDialog(onDismiss = { isAlertDialogVisible.value = false }) {
//                                Column (
//                                    modifier = Modifier
//                                        .background(white, shape = Shapes.medium)
//                                        .padding(20.dp, 24.dp)
//
//                                ) {
//                                    Row (
//                                    ) {
//                                        Box(
//                                            modifier = Modifier
//                                                .height(48.dp)
//                                                .width(48.dp)
//                                                .background(green100, shape = CircleShape),
//                                            contentAlignment = Alignment.Center
//                                        ) {
//                                            Icon(
//                                                imageVector = notificationIcon,
//                                                contentDescription = null,
//                                                modifier = Modifier.size(24.dp)
//                                            )
//                                        }
//
//
//                                        Spacer(modifier = Modifier.width(12.dp))
//
//                                        Column (
//                                            modifier = Modifier
//                                        ) {
//                                            Text(
//                                                text = stringResource(R.string.novo_alerta_title),
//                                                fontSize = 14.sp,
//                                                fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                                color = gray500
//                                            )
//                                            Spacer(modifier = Modifier.height(4.dp))
//                                            Text(
//                                                text = "O iluminado",
//                                                fontSize = 16.sp,
//                                                fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                                color = green600
//                                            )
//                                        }
//
//                                    }
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//                                    Box(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(2.dp)
//                                            .background(gray200)
//                                    ) {}
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Row {
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.Top)
//                                                .width(290.dp)
//                                        ){
//                                            Text(
//                                                text = stringResource(R.string.alert_localization_title),
//                                                fontSize = 14.sp,
//                                                fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                                color = green900
//                                            )
//                                            Spacer(modifier = Modifier.height(4.dp))
//                                            Text(
//                                                text = stringResource(R.string.alert_localization_description),
//                                                fontSize = 12.sp,
//                                                fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                                color = gray500
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(28.dp))
//                                        Box{
//                                            Switch(
//                                                checked = applyLocalizationInAlert.value,
//                                                onCheckedChange = {applyLocalizationInAlert.value = it } ,
//                                                modifier = Modifier
//                                                    .size(12.dp)
//                                            )
//                                        }
//                                    }
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//                                    Box(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(2.dp)
//                                            .background(gray200)
//                                    ) {}
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Row {
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.Top)
//                                                .width(290.dp)
//                                        ) {
//                                            Text(
//                                                text = stringResource(R.string.alert_user_send_title),
//                                                fontSize = 14.sp,
//                                                fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                                color = green900
//                                            )
//                                            Spacer(modifier = Modifier.height(4.dp))
//                                            Text(
//                                                text = stringResource(R.string.alert_use_send_description),
//                                                fontSize = 12.sp,
//                                                fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                                color = gray500
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(28.dp))
//                                        Box {
//                                            Switch(
//                                                checked = applyUserSendInAlert.value,
//                                                onCheckedChange = {applyUserSendInAlert.value = it },
//                                                modifier = Modifier
//                                                    .size(12.dp)
//                                            )
//                                        }
//                                    }
//
//                                    Spacer(modifier = Modifier.height(16.dp))
//                                    Button(
//                                        onClick = {isAlertDialogVisible.value = false},
//                                        colors = ButtonDefaults.buttonColors(green600),
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(50.dp)
//                                    ) {
//                                        Text(
//                                            text = stringResource(id = R.string.create_alert_button),
//                                            color = white,
//                                            fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                            fontSize = 14.sp
//                                        )
//                                    }
//
//                                }
//                            }
//                        }
//
//                        Spacer(modifier = Modifier.height(6.dp))
//                        if (pesquisouLivro.value) {
//                            Image(
//                                painter = painterResource(id = R.drawable.not_found),
//                                contentDescription = "nao encontrado",
//                                contentScale = ContentScale.Crop,
//                                modifier = Modifier
//                                    .height(146.dp)
//                                    .height(220.dp)
//                                    .align(Alignment.CenterHorizontally)
//                            )
//
//                            Spacer(modifier = Modifier.height(24.dp))
//
//                            Text(
//                                text = stringResource(R.string.search_not_found_title),
//                                color = green900,
//                                fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                fontSize = 16.sp,
//                                modifier = Modifier
//                                    .align(Alignment.CenterHorizontally)
//                            )
//
//                            Spacer(modifier = Modifier.height(8.dp))
//
//                            Text(
//                                text = stringResource(R.string.search_not_found_description),
//                                color = gray500,
//                                fontFamily = Lato,
//                                fontSize = 14.sp,
//                                textAlign = TextAlign.Center
//                            )
//
//                            Spacer(modifier = Modifier.height(12.dp))
//
//                            Button(
//                                onClick = { isAlertDialogVisible.value = true },
//                                colors = ButtonDefaults.buttonColors(green600),
//                                modifier = Modifier
//                                    .height(46.dp)
//                                    .align(Alignment.CenterHorizontally)
//                            ) {
//                                Text(
//                                    text = stringResource(R.string.create_alert_button),
//                                    color = white,
//                                    fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                    fontSize = 14.sp
//                                )
//                            }
//                        } else {
//
//                            /* ***** Item Livro Lista ***** */
//                            Row (
//                                modifier = Modifier
//                                    .background(white)
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes),
//                                    contentDescription = null,
//                                    modifier = Modifier
//                                        .height(192.dp)
//                                        .width(115.dp)
//                                        .clip(RoundedCornerShape(6.dp))
//                                )
//                                Column (
//                                    modifier = Modifier.
//                                    padding(16.dp)
//                                ) {
//                                    /* ***** Detalhes do livro ***** */
//                                    Row() {
//                                        Text(
//                                            text = bookDatailsMock.genero,
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                            fontWeight = FontWeight.SemiBold
//                                        )
//
//                                        Text(
//                                            text = " - ",
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                        )
//
//                                        Text(
//                                            text = bookDatailsMock.edicao,
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(2.dp))
//
//                                    Text(
//                                        text = bookDatailsMock.titulo,
//                                        color = green900,
//                                        fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                        fontSize = 14.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(4.dp))
//
//                                    Text(
//                                        text = bookDatailsMock.autor,
//                                        color = gray500,
//                                        fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                        fontSize = 12.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Box(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(2.dp)
//                                            .background(gray200)
//                                    ) {}
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    /* ***** Detalhes do pedido ***** */
//                                    Row {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.profile),
//                                            contentDescription = null,
//                                            contentScale = ContentScale.Crop,
//                                            modifier = Modifier
//                                                .width(44.dp)
//                                                .height(44.dp)
//                                                .clip(RoundedCornerShape(22.dp))
//                                        )
//
//                                        Spacer(modifier = Modifier.width(8.dp))
//
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.CenterVertically)
//                                        ) {
//                                            Text(
//                                                text = pedidoTrocaMock.nomePessoa,
//                                                fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                                fontSize = 12.sp,
//                                                color = gray500
//                                            )
//
//                                            Text(
//                                                text = bookDatailsMock.podeBuscar,
//                                                fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                                fontSize = 11.sp,
//                                                color = green600
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(48.dp))
//
//                                    }
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Row {
//                                        Icon(
//                                            imageVector = Icons.Default.LocationOn,
//                                            contentDescription = null,
//                                            modifier = Modifier.size(16.dp),
//                                            tint = Color.Red
//                                        )
//
//                                        Spacer(modifier = Modifier.width(8.dp))
//
//                                        Text(
//                                            text = pedidoTrocaMock.localPessoa,
//                                            fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                            fontSize = 12.sp,
//                                            color = gray500
//                                        )
//                                    }
//                                }
//                            }
//
//                            Spacer(modifier = Modifier.height(12.dp))
//
//                            /* ***** Item Livro Lista ***** */
//                            Row (
//                                modifier = Modifier
//                                    .background(white)
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes),
//                                    contentDescription = null,
//                                    modifier = Modifier
//                                        .height(192.dp)
//                                        .width(115.dp)
//                                        .clip(RoundedCornerShape(6.dp))
//                                )
//                                Column (
//                                    modifier = Modifier.
//                                    padding(16.dp)
//                                ) {
//                                    /* ***** Detalhes do livro ***** */
//                                    Row() {
//                                        Text(
//                                            text = bookDatailsMock.genero,
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                            fontWeight = FontWeight.SemiBold
//                                        )
//
//                                        Text(
//                                            text = " - ",
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                        )
//
//                                        Text(
//                                            text = bookDatailsMock.edicao,
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(2.dp))
//
//                                    Text(
//                                        text = bookDatailsMock.titulo,
//                                        color = green900,
//                                        fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                        fontSize = 14.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(4.dp))
//
//                                    Text(
//                                        text = bookDatailsMock.autor,
//                                        color = gray500,
//                                        fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                        fontSize = 12.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Box(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(2.dp)
//                                            .background(gray200)
//                                    ) {}
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    /* ***** Detalhes do pedido ***** */
//                                    Row {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.profile),
//                                            contentDescription = null,
//                                            contentScale = ContentScale.Crop,
//                                            modifier = Modifier
//                                                .width(44.dp)
//                                                .height(44.dp)
//                                                .clip(RoundedCornerShape(22.dp))
//                                        )
//
//                                        Spacer(modifier = Modifier.width(8.dp))
//
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.CenterVertically)
//                                        ) {
//                                            Text(
//                                                text = pedidoTrocaMock.nomePessoa,
//                                                fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                                fontSize = 12.sp,
//                                                color = gray500
//                                            )
//
//                                            Text(
//                                                text = bookDatailsMock.podeBuscar,
//                                                fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                                fontSize = 11.sp,
//                                                color = green600
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(48.dp))
//
//                                    }
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Row {
//                                        Icon(
//                                            imageVector = Icons.Default.LocationOn,
//                                            contentDescription = null,
//                                            modifier = Modifier.size(16.dp),
//                                            tint = Color.Red
//                                        )
//
//                                        Spacer(modifier = Modifier.width(8.dp))
//
//                                        Text(
//                                            text = pedidoTrocaMock.localPessoa,
//                                            fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                            fontSize = 12.sp,
//                                            color = gray500
//                                        )
//                                    }
//                                }
//                            }
//
//                            Spacer(modifier = Modifier.height(12.dp))
//
//                            /* ***** Item Livro Lista ***** */
//                            Row (
//                                modifier = Modifier
//                                    .background(white)
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes),
//                                    contentDescription = null,
//                                    modifier = Modifier
//                                        .height(192.dp)
//                                        .width(115.dp)
//                                        .clip(RoundedCornerShape(6.dp))
//                                )
//                                Column (
//                                    modifier = Modifier.
//                                    padding(16.dp)
//                                ) {
//                                    /* ***** Detalhes do livro ***** */
//                                    Row() {
//                                        Text(
//                                            text = bookDatailsMock.genero,
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                            fontWeight = FontWeight.SemiBold
//                                        )
//
//                                        Text(
//                                            text = " - ",
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                        )
//
//                                        Text(
//                                            text = bookDatailsMock.edicao,
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(2.dp))
//
//                                    Text(
//                                        text = bookDatailsMock.titulo,
//                                        color = green900,
//                                        fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                        fontSize = 14.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(4.dp))
//
//                                    Text(
//                                        text = bookDatailsMock.autor,
//                                        color = gray500,
//                                        fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                        fontSize = 12.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Box(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(2.dp)
//                                            .background(gray200)
//                                    ) {}
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    /* ***** Detalhes do pedido ***** */
//                                    Row {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.profile),
//                                            contentDescription = null,
//                                            contentScale = ContentScale.Crop,
//                                            modifier = Modifier
//                                                .width(44.dp)
//                                                .height(44.dp)
//                                                .clip(RoundedCornerShape(22.dp))
//                                        )
//
//                                        Spacer(modifier = Modifier.width(8.dp))
//
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.CenterVertically)
//                                        ) {
//                                            Text(
//                                                text = pedidoTrocaMock.nomePessoa,
//                                                fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                                fontSize = 12.sp,
//                                                color = gray500
//                                            )
//
//                                            Text(
//                                                text = bookDatailsMock.podeBuscar,
//                                                fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                                fontSize = 11.sp,
//                                                color = green600
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(48.dp))
//
//                                    }
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Row {
//                                        Icon(
//                                            imageVector = Icons.Default.LocationOn,
//                                            contentDescription = null,
//                                            modifier = Modifier.size(16.dp),
//                                            tint = Color.Red
//                                        )
//
//                                        Spacer(modifier = Modifier.width(8.dp))
//
//                                        Text(
//                                            text = pedidoTrocaMock.localPessoa,
//                                            fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                            fontSize = 12.sp,
//                                            color = gray500
//                                        )
//                                    }
//                                }
//                            }
//
//                            Spacer(modifier = Modifier.height(12.dp))
//
//                            /* ***** Item Livro Lista ***** */
//                            Row (
//                                modifier = Modifier
//                                    .background(white)
//                            ) {
//                                Image(
//                                    painter = painterResource(id = R.drawable.morro_dos_ventos_uivantes),
//                                    contentDescription = null,
//                                    modifier = Modifier
//                                        .height(192.dp)
//                                        .width(115.dp)
//                                        .clip(RoundedCornerShape(6.dp))
//                                )
//                                Column (
//                                    modifier = Modifier.
//                                    padding(16.dp)
//                                ) {
//                                    /* ***** Detalhes do livro ***** */
//                                    Row() {
//                                        Text(
//                                            text = bookDatailsMock.genero,
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                            fontWeight = FontWeight.SemiBold
//                                        )
//
//                                        Text(
//                                            text = " - ",
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                        )
//
//                                        Text(
//                                            text = bookDatailsMock.edicao,
//                                            color = green500,
//                                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
//                                        )
//                                    }
//
//                                    Spacer(modifier = Modifier.height(2.dp))
//
//                                    Text(
//                                        text = bookDatailsMock.titulo,
//                                        color = green900,
//                                        fontFamily = FontFamily(Font(R.font.lato_bold)),
//                                        fontSize = 14.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(4.dp))
//
//                                    Text(
//                                        text = bookDatailsMock.autor,
//                                        color = gray500,
//                                        fontFamily = FontFamily(Font(R.font.inter_regular)),
//                                        fontSize = 12.sp
//                                    )
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Box(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(2.dp)
//                                            .background(gray200)
//                                    ) {}
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    /* ***** Detalhes do pedido ***** */
//                                    Row {
//                                        Image(
//                                            painter = painterResource(id = R.drawable.profile),
//                                            contentDescription = null,
//                                            contentScale = ContentScale.Crop,
//                                            modifier = Modifier
//                                                .width(44.dp)
//                                                .height(44.dp)
//                                                .clip(RoundedCornerShape(22.dp))
//                                        )
//
//                                        Spacer(modifier = Modifier.width(8.dp))
//
//                                        Column (
//                                            modifier = Modifier
//                                                .align(Alignment.CenterVertically)
//                                        ) {
//                                            Text(
//                                                text = pedidoTrocaMock.nomePessoa,
//                                                fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                                fontSize = 12.sp,
//                                                color = gray500
//                                            )
//
//                                            Text(
//                                                text = bookDatailsMock.podeBuscar,
//                                                fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                                fontSize = 11.sp,
//                                                color = green600
//                                            )
//                                        }
//
//                                        Spacer(modifier = Modifier.width(48.dp))
//
//                                    }
//
//                                    Spacer(modifier = Modifier.height(12.dp))
//
//                                    Row {
//                                        Icon(
//                                            imageVector = Icons.Default.LocationOn,
//                                            contentDescription = null,
//                                            modifier = Modifier.size(16.dp),
//                                            tint = Color.Red
//                                        )
//
//                                        Spacer(modifier = Modifier.width(8.dp))
//
//                                        Text(
//                                            text = pedidoTrocaMock.localPessoa,
//                                            fontFamily = FontFamily(Font(R.font.inter_medium)),
//                                            fontSize = 12.sp,
//                                            color = gray500
//                                        )
//                                    }
//                                }
//                            }
//                        }
//
//                    }
//                }
//            }
//        }
   }
}