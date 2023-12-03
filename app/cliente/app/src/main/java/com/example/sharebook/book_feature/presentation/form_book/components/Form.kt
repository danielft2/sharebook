package com.example.sharebook.book_feature.presentation.form_book.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.sharebook.core.presentation.components.divider.DividerCustom
import com.example.sharebook.core.presentation.components.input.TextFieldCustom
import com.example.sharebook.core.presentation.components.input.SelectFieldCustom
import com.example.sharebook.R
import com.example.sharebook.book_feature.presentation.form_book.ImagesAdditional
import com.example.sharebook.book_feature.presentation.form_book.event.FormBookEvent
import com.example.sharebook.book_feature.presentation.form_book.state.UiState
import com.example.sharebook.core.presentation.components.input.FileInputCustom
import com.example.sharebook.core.presentation.components.input.SwitchWithTextCustom

@Composable
fun Form(
    uiState: UiState,
    onChange: (event: FormBookEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_isbn),
            value = uiState.isbn,
            errorMessage = uiState.isbnError.asString(),
            onChange = { onChange(FormBookEvent.IsbnChange(it)) }
        )

        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_name),
            value = uiState.nome,
            errorMessage = uiState.nomeError.asString(),
            onChange = { onChange(FormBookEvent.NomeChange(it)) }
        )

        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_gender),
            value = uiState.genero,
            errorMessage = uiState.generoError.asString(),
            onChange = { onChange(FormBookEvent.GeneroChange(it)) }
        )

        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_author),
            value = uiState.autor,
            errorMessage = uiState.autorError.asString(),
            onChange = {  onChange(FormBookEvent.AutorChange(it)) }
        )

        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_edition),
            value = uiState.edicao,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            errorMessage = uiState.edicaoError.asString(),
            onChange = { onChange(FormBookEvent.EdicaoChange(it)) }
        )

        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_language),
            value = uiState.idioma,
            errorMessage = uiState.idiomaError.asString(),
            onChange = { onChange(FormBookEvent.IdiomaChange(it)) }
        )

        SelectFieldCustom(
            label = stringResource(id = R.string.form_book_field_state),
            itens = uiState.statesItens,
            value = uiState.estado,
            errorMessage = uiState.estadoError.asString(),
            onChange = { onChange(FormBookEvent.EstadoChange(it)) }
        )

        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_synopsis),
            value = uiState.sinopse,
            singleLine = false,
            errorMessage = uiState.sinopseError.asString(),
            onChange = { onChange(FormBookEvent.SinopseChange(it)) },
            modifier = Modifier.height(80.dp)
        )

        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_lat),
            value = uiState.latitude,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            errorMessage = uiState.latitudeError.asString(),
            onChange = { onChange(FormBookEvent.LatitudeChange(it)) }
        )

        TextFieldCustom(
            label = stringResource(id = R.string.form_book_field_lon),
            value = uiState.longitude,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            errorMessage = uiState.longitudeError.asString(),
            onChange = { onChange(FormBookEvent.LongitudeChange(it)) }
        )

        FileInputCustom(
            label = stringResource(id = R.string.form_book_field_cover),
            value = uiState.capa?.name ?: stringResource(id = R.string.form_book_field_cover_placeholder),
            errorMessage = uiState.capaError.asString(),
            onChange = { onChange(FormBookEvent.CapaChange(it)) }
        )

        ImagesAdditional { index, file, isAdd ->
            onChange(FormBookEvent.ImagensChange(index, file, isAdd))
        }

        Column {
            DividerCustom(spaceBottom = 12.dp)

            SwitchWithTextCustom(
                title = stringResource(id = R.string.check_get_preference),
                description = stringResource(id = R.string.description_get_preference),
                checked = uiState.preferenciaBuscar,
                onCheckedChange = { onChange(FormBookEvent.BuscarChange(it)) }
            )
        }

        Column {
            DividerCustom(spaceBottom = 12.dp)

            SwitchWithTextCustom(
                title = stringResource(id = R.string.check_receive_preference),
                description = stringResource(id = R.string.description_receive_preference),
                checked = uiState.preferenciaReceber,
                onCheckedChange = { onChange(FormBookEvent.ReceberChange(it)) }
            )
        }
    }
}