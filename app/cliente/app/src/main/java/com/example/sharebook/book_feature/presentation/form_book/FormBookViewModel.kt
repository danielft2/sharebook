package com.example.sharebook.book_feature.presentation.form_book

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.R
import com.example.sharebook.book_feature.data.remote.model.toSelectItem
import com.example.sharebook.book_feature.domain.usecase.CreateBookUseCase
import com.example.sharebook.book_feature.domain.usecase.DetailsBookUseCase
import com.example.sharebook.book_feature.domain.usecase.ListStatesUseCase
import com.example.sharebook.book_feature.domain.usecase.UpdateBookUseCase
import com.example.sharebook.book_feature.presentation.form_book.channel.FormBookRequestChannel
import com.example.sharebook.book_feature.presentation.form_book.event.FormBookEvent
import com.example.sharebook.book_feature.presentation.form_book.state.UiState
import com.example.sharebook.book_feature.presentation.form_book.state.toFormBookModel
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.domain.usecase.ValidateRequiredUseCase
import com.example.sharebook.core.utils.Constants
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.core.utils.UiText
import com.example.sharebook.core.utils.ValidationFieldResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class FormBookViewModel @Inject constructor(
    private val createBookUseCase: CreateBookUseCase,
    private val updateBookUseCase: UpdateBookUseCase,
    private val listStatesUseCase: ListStatesUseCase,
    private val detailsBookUseCase: DetailsBookUseCase,
    private val userStorageManagement: UserStorageManagement,
    private val validateRequiredUseCase: ValidateRequiredUseCase,
    stateHandle: SavedStateHandle
) : ViewModel() {
    private val formBookRequestChannel = Channel<FormBookRequestChannel>()
    val formBookRequestState = formBookRequestChannel.receiveAsFlow()

    var uiState by mutableStateOf(UiState())
        private set

    init {
        val user = runBlocking { userStorageManagement.retry().first() }
        uiState = uiState.copy(userLogged = user)
        getStatesBook()

        stateHandle.get<String>(Constants.BOOK_PARAM_ID)?.let { bookId ->
            uiState = uiState.copy(bookId = bookId)
        }
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
            is FormBookEvent.LongitudeChange -> { uiState = uiState.copy(longitude = event.longitude) }

            is FormBookEvent.Submit -> { submitForm() }
        }
    }

    private fun submitForm() {
        val validateList: MutableList<ValidationFieldResult>

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

        validateList = mutableListOf(
            validateNameResult,
            validateGeneroResult,
            validateAutorResult,
            validateEdicaoResult,
            validateIdiomaResult,
            validateSinopseResult,
            validateEstadoResult
        )

        if (uiState.bookId.isNullOrEmpty()) {
            validateList.addAll(
                listOf(
                    validateIsbnResult,
                    validateLatitudeResult,
                    validateLongitudeResult,
                    validateCapaResult
                )
            )
        }

        val hasError = validateList.any { !it.isValid }

        if (hasError) {
            uiState = uiState.copy(
                nomeError = UiText.StringResource(resId = validateNameResult.resId),
                generoError = UiText.StringResource(resId = validateGeneroResult.resId),
                autorError = UiText.StringResource(resId = validateAutorResult.resId),
                edicaoError = UiText.StringResource(resId = validateEdicaoResult.resId),
                idiomaError = UiText.StringResource(resId = validateIdiomaResult.resId),
                sinopseError = UiText.StringResource(resId = validateSinopseResult.resId),
                estadoError = UiText.StringResource(resId = validateEstadoResult.resId),
                capaError = UiText.StringResource(resId = validateCapaResult.resId),
            )

            if (uiState.bookId.isNullOrEmpty()) {
                uiState = uiState.copy(
                    isbnError = UiText.StringResource(resId = validateIsbnResult.resId),
                    latitudeError = UiText.StringResource(resId = validateLatitudeResult.resId),
                    longitudeError = UiText.StringResource(resId = validateLongitudeResult.resId),
                )
            }

            return
        }

        if (uiState.bookId.isNullOrEmpty()) createBook()
        else updateBook()
    }

    private fun createBook() {
        viewModelScope.launch {
            createBookUseCase(uiState.toFormBookModel()).collect {response ->
                when (response) {
                    is Resource.Success -> { formBookRequestChannel.send(FormBookRequestChannel.Success(
                        R.string.create_book_request_success
                    )) }
                    is Resource.Error -> {
                        formBookRequestChannel.send(FormBookRequestChannel.Error(response.message!!))
                    }
                    is Resource.Loading -> { uiState = uiState.copy(isLoadingFormRequest = true) }
                    is Resource.Finnaly -> { uiState = uiState.copy(isLoadingFormRequest = false) }
                }
            }
        }
    }

    private fun updateBook() {
        viewModelScope.launch {
            updateBookUseCase(uiState.bookId!!, uiState.toFormBookModel()).collect {response ->
                when (response) {
                    is Resource.Success -> { formBookRequestChannel.send(FormBookRequestChannel.Success(
                        R.string.update_book_request_success
                    )) }
                    is Resource.Error -> {
                        formBookRequestChannel.send(FormBookRequestChannel.Error(response.message!!))
                    }
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
                       if (!uiState.bookId.isNullOrEmpty()) getBookData(uiState.bookId!!)
                   }
                   is Resource.Error -> {
                       uiState = uiState.copy(
                           isErrorStateRequest = response.message,
                           isLoadingStateRequest = false
                       )
                   }
                   is Resource.Loading -> {
                       uiState = uiState.copy(isLoadingStateRequest = true)
                   }
                   is Resource.Finnaly -> {}
               }
           }
       }
    }

    private fun getBookData(bookId: String) {
        viewModelScope.launch {
            detailsBookUseCase(bookId).collect {response ->
                uiState = when(response) {
                    is Resource.Success -> {
                        uiState.copy(
                            isbn = response.data!!.isbn,
                            nome = response.data.name,
                            genero = response.data.genders,
                            autor = response.data.authors,
                            edicao = response.data.edition.toString(),
                            idioma = response.data.language,
                            estado = response.data.bookState.toSelectItem(),
                            sinopse = response.data.synopsis,
                            preferenciaReceber = response.data.canReceive,
                            preferenciaBuscar = response.data.canSearch
                        )
                    }
                    is Resource.Error -> { uiState.copy(isErrorStateRequest = response.message) }
                    is Resource.Loading -> { uiState.copy(isLoadingStateRequest = true) }
                    is Resource.Finnaly -> { uiState.copy(isLoadingStateRequest = false) }
                }
            }
        }
    }
}