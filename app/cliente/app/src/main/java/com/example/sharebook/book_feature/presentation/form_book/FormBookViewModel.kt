package com.example.sharebook.book_feature.presentation.form_book

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.book_feature.domain.usecase.CreateBookUseCase
import com.example.sharebook.book_feature.domain.usecase.ListStatesUseCase
import com.example.sharebook.book_feature.presentation.form_book.event.FormBookEvent
import com.example.sharebook.book_feature.presentation.form_book.state.UiState
import com.example.sharebook.book_feature.presentation.form_book.state.toFormBookModel
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.domain.usecase.ValidateRequiredUseCase
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.core.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class FormBookViewModel @Inject constructor(
    private val createBookUseCase: CreateBookUseCase,
    private val listStatesUseCase: ListStatesUseCase,
    private val userStorageManagement: UserStorageManagement,
    private val validateRequiredUseCase: ValidateRequiredUseCase
) : ViewModel() {
    var uiState by mutableStateOf(UiState())
        private set

    init {
        val user = runBlocking { userStorageManagement.retry().first() }
        uiState = uiState.copy(userLogged = user)
        getStatesBook()
    }

    fun onEvent(event: FormBookEvent) {
        when (event) {
            is FormBookEvent.AutorChange -> { uiState = uiState.copy(autor = event.autor) }
            is FormBookEvent.BuscarChange -> { uiState = uiState.copy(preferenciaBuscar = event.preferencia) }
            is FormBookEvent.CapaChange -> { uiState = uiState.copy(capa = event.capaLivro) }
            is FormBookEvent.EdicaoChange -> { uiState = uiState.copy(edicao = event.edicao) }
            is FormBookEvent.EstadoChange -> { uiState = uiState.copy(estado = event.estadoLivro) }
            is FormBookEvent.GeneroChange -> { uiState = uiState.copy(genero = event.genero) }
            is FormBookEvent.IdiomaChange -> { uiState = uiState.copy(idioma = event.idioma) }
            is FormBookEvent.ImagensChange -> {
                val imagens = uiState.imagens

                if (event.isAdd) imagens.add(event.imagemLivro)
                else imagens.remove(event.imagemLivro)

                uiState = uiState.copy(imagens = imagens)
            }
            is FormBookEvent.IsbnChange -> { uiState = uiState.copy(isbn = event.isbn) }
            is FormBookEvent.NomeChange -> { uiState = uiState.copy(nome = event.nome) }
            is FormBookEvent.ReceberChange -> { uiState = uiState.copy(preferenciaReceber = event.preferencia) }
            is FormBookEvent.SinopseChange -> { uiState = uiState.copy(sinopse = event.sinopse) }
            is FormBookEvent.LatitudeChange -> { uiState = uiState.copy(latitude = event.latitude) }
            is FormBookEvent.LongitudeChange -> { uiState = uiState.copy(latitude = event.longitude) }

            is FormBookEvent.Submit -> { submitForm() }
        }
    }

    private fun submitForm() {
        val validateNameResult = validateRequiredUseCase(uiState.nome)
        val validateGeneroResult = validateRequiredUseCase(uiState.genero)
        val validateIsbnResult = validateRequiredUseCase(uiState.isbn)
        val validateAutorResult = validateRequiredUseCase(uiState.autor)
        val validateEdicaoResult = validateRequiredUseCase(uiState.edicao)
        val validateIdiomaResult = validateRequiredUseCase(uiState.idioma)
        val validateSinopseResult = validateRequiredUseCase(uiState.sinopse)
        val validateLatitudeResult = validateRequiredUseCase(uiState.latitude)
        val validateLongitudeResult = validateRequiredUseCase(uiState.longitude)
        val validateEstadoResult = validateRequiredUseCase(uiState.estado.value)
        val validateCapaResult = validateRequiredUseCase(uiState.capa?.name ?: "")

        val hasError = listOf(
            validateNameResult,
            validateGeneroResult,
            validateIsbnResult,
            validateAutorResult,
            validateEdicaoResult,
            validateIdiomaResult,
            validateLatitudeResult,
            validateLongitudeResult,
            validateSinopseResult,
            validateEstadoResult,
            validateCapaResult,
        ).any { !it.isValid }

        if (hasError) {
            uiState = uiState.copy(
                nomeError = UiText.StringResource(resId = validateNameResult.resId),
                generoError = UiText.StringResource(resId = validateGeneroResult.resId),
                isbnError = UiText.StringResource(resId = validateIsbnResult.resId),
                autorError = UiText.StringResource(resId = validateAutorResult.resId),
                edicaoError = UiText.StringResource(resId = validateEdicaoResult.resId),
                idiomaError = UiText.StringResource(resId = validateIdiomaResult.resId),
                sinopseError = UiText.StringResource(resId = validateSinopseResult.resId),
                latitudeError = UiText.StringResource(resId = validateLatitudeResult.resId),
                longitudeError = UiText.StringResource(resId = validateLongitudeResult.resId),
                estadoError = UiText.StringResource(resId = validateEstadoResult.resId),
                capaError = UiText.StringResource(resId = validateCapaResult.resId),
            )
            return
        }
        createBook()
    }

    private fun createBook() {
        viewModelScope.launch {
            createBookUseCase(uiState.toFormBookModel()).collect {response ->
                when (response) {
                    is Resource.Success -> { println(response.data) }
                    is Resource.Error -> { println(response.message) }
                    is Resource.Loading -> { uiState = uiState.copy(isLoadingFormRequest = true) }
                    is Resource.Finnaly -> { uiState = uiState.copy(isLoadingFormRequest = false) }
                }
            }
        }
    }

    private fun getStatesBook() {
       viewModelScope.launch {
           listStatesUseCase().collect { response ->
               when (response) {
                   is Resource.Success -> {
                       uiState = uiState.copy(statesItens = response.data ?: listOf())
                   }
                   is Resource.Error -> { uiState = uiState.copy(isErrorStateRequest = response.message) }
                   is Resource.Loading -> {
                       uiState = uiState.copy(isLoadingStateRequest = true)
                   }
                   is Resource.Finnaly -> {
                       uiState = uiState.copy(isLoadingStateRequest = false)
                   }
               }
           }
       }
    }
}