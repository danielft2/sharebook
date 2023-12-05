package com.example.sharebook.book_management_feature.presentation.add_book.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.auth_feature.presentation.register.event.RegisterFormEvent
import com.example.sharebook.book_management_feature.presentation.add_book.AddBookViewModel
import com.example.sharebook.book_management_feature.presentation.add_book.event.AddBookFormEvent
import com.example.sharebook.core.presentation.components.CheckboxFieldCustom
import com.example.sharebook.core.presentation.components.ImageFieldCustom
import com.example.sharebook.core.presentation.components.SelectFieldCustom
import com.example.sharebook.core.presentation.components.TextFieldCustom
import com.example.sharebook.core.presentation.components.button.ButtonPrimary
import com.example.sharebook.core.presentation.ui.theme.green500
import com.example.sharebook.core.utils.UiText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

val listOfBookStates = listOf("Muito Desgastado", "Pouco Desgastado", "Sem Desgaste", "Lacrado")

@Composable
fun AddBook(
    viewModel: AddBookViewModel = hiltViewModel(),
    navController: NavController,
) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color(0xFFF0F8F7), Color(0xFFF8F8F8)),
                        startY = 0f,
                        endY = 500f
                    )
                )
                .padding(0.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            AddBookHeader { navController.popBackStack() }

            Column(
                modifier = Modifier
                    .weight(1f, false)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFFF0F8F7), Color(0xFFF8F8F8)),
                            startY = 0f,
                            endY = 500f
                        )
                    )
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
            ) {
                Spacer(modifier = Modifier.height(24.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_isbn).asString(),
                    value = viewModel.uiFormState.isbn,
                    enable = !viewModel.uiFormState.isbnSearchIsLoading,
                    onChange = { viewModel.onEvent(AddBookFormEvent.IsbnChange(it)) },
                    errorMessage = viewModel.uiFormState.isbnError.asString(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_book_name).asString(),
                    value = viewModel.uiFormState.nome,
                    onChange = { viewModel.onEvent(AddBookFormEvent.NomeChange(it)) },
                    errorMessage = viewModel.uiFormState.nomeError.asString()
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_genre).asString(),
                    value = viewModel.uiFormState.genero,
                    onChange = { viewModel.onEvent(AddBookFormEvent.GeneroChange(it)) },
                    errorMessage = viewModel.uiFormState.generoError.asString()
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_author).asString(),
                    value = viewModel.uiFormState.autor,
                    onChange = { viewModel.onEvent(AddBookFormEvent.AutorChange(it)) },
                    errorMessage = viewModel.uiFormState.autorError.asString()
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_edition).asString(),
                    value = viewModel.uiFormState.edicao,
                    onChange = { viewModel.onEvent(AddBookFormEvent.EdicaoChange(it)) },
                    errorMessage = viewModel.uiFormState.edicaoError.asString()
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_language).asString(),
                    value = viewModel.uiFormState.idioma,
                    onChange = { viewModel.onEvent(AddBookFormEvent.IdiomaChange(it)) },
                    errorMessage = viewModel.uiFormState.idiomaError.asString()
                )

                Spacer(modifier = Modifier.height(18.dp))

                SelectFieldCustom(
                    label = UiText.StringResource(R.string.field_book_state).asString(),
                    items = listOfBookStates,
                    selectedItem =
                        if(viewModel.uiFormState.estadoLivro.isNullOrEmpty())
                            "Selecione o Estado do Livro"
                        else
                            viewModel.uiFormState.estadoLivro,
                    onItemSelected = { viewModel.onEvent(AddBookFormEvent.EstadoLivroChange(it)) },
                    errorMessage = viewModel.uiFormState.estadoLivroError.asString()
                )

                Spacer(modifier = Modifier.height(18.dp))

                CheckboxFieldCustom(
                    title = UiText.StringResource(R.string.check_get_preference).asString(),
                    description = UiText.StringResource(R.string.description_get_preference).asString(),
                    checked = viewModel.uiFormState.preferenciaBuscar,
                    onCheckedChange = { viewModel.onEvent(AddBookFormEvent.BuscarChange(it)) }
                )

                Spacer(modifier = Modifier.height(18.dp))

                CheckboxFieldCustom(
                    title = UiText.StringResource(R.string.check_receive_preference).asString(),
                    description = UiText.StringResource(R.string.description_receive_preference).asString(),
                    checked = viewModel.uiFormState.preferenciaReceber,
                    onCheckedChange = { viewModel.onEvent(AddBookFormEvent.ReceberChange(it)) }
                )

                Spacer(modifier = Modifier.height(18.dp))

                ImageFieldCustom(
                    label = UiText.StringResource(R.string.book_cape_title).asString(),
                    onImageSelected = { viewModel.onEvent(AddBookFormEvent.CapaLivroChange(it)) },
                    description = UiText.StringResource(R.string.book_cape_field).asString()
                )

                Spacer(modifier = Modifier.height(18.dp))

                ImageFieldCustom(
                    label = UiText.StringResource(R.string.book_images_title).asString(),
                    onImageSelected = { viewModel.onEvent(AddBookFormEvent.ImagemLivroChange(it)) },
                    description = UiText.StringResource(R.string.book_images_field).asString()
                )

                Spacer(modifier = Modifier.height(18.dp))
            }

            Box(modifier = Modifier.padding(20.dp)) {
                ButtonPrimary(
                    text = UiText.StringResource(R.string.save_book).asString(),
                    loading = viewModel.requestState.isLoading,
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            delay(2000) // 1000 milissegundos = 1 segundo
                            navController.popBackStack()
                        }
                    }
                )
            }
        }
        if(!viewModel.requestState.error.isNullOrEmpty()) {
            Toast.makeText(LocalContext.current, viewModel.requestState.error, Toast.LENGTH_SHORT).show()
        }
    }
}