package com.example.sharebook.book_management_feature.presentation.add_book

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.R
import com.example.sharebook.book_management_feature.domain.usecase.AddBookUseCases
import com.example.sharebook.book_management_feature.domain.usecase.validations.ValidateUseCases
import com.example.sharebook.book_management_feature.presentation.add_book.event.AddBookFormEvent
import com.example.sharebook.book_management_feature.presentation.add_book.state.AddBookFormState
import com.example.sharebook.book_management_feature.presentation.add_book.state.AddBookRequestState
import com.example.sharebook.book_management_feature.presentation.add_book.state.toAddBookModel
import com.example.sharebook.core.domain.usecase.ConsultIsbnUseCase
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.core.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val addBookUseCases: AddBookUseCases,
    /*private val consultIsbnUseCase: ConsultIsbnUseCase,*/
    private val validateUseCase: ValidateUseCases
) : ViewModel() {
    var uiFormState by mutableStateOf(AddBookFormState())
        private set

    var requestState by mutableStateOf(AddBookRequestState())
        private set

    fun onEvent(event: AddBookFormEvent) {
        when (event) {
            is AddBookFormEvent.NomeChange -> {
                uiFormState = uiFormState.copy(nome = event.nome)
            }

            is AddBookFormEvent.GeneroChange -> {
                uiFormState = uiFormState.copy(genero = event.genero)
            }

            is AddBookFormEvent.AutorChange -> {
                uiFormState = uiFormState.copy(autor = event.autor)
            }

            is AddBookFormEvent.EdicaoChange -> {
                uiFormState = uiFormState.copy(edicao = event.edicao)
            }

            is AddBookFormEvent.IdiomaChange -> {
                uiFormState = uiFormState.copy(idioma = event.idioma)
            }

            is AddBookFormEvent.EstadoLivroChange -> {
                uiFormState = uiFormState.copy(estadoLivro = event.estadoLivroChange)
            }

            is AddBookFormEvent.IsbnChange -> {
                uiFormState = uiFormState.copy(isbn = event.isbn)
                /*if(uiFormState.isbn.length == 10 || uiFormState.isbn.length == 13) consultIsbn()*/
            }

            is AddBookFormEvent.BuscarChange -> {
                uiFormState = uiFormState.copy(preferenciaBuscar = event.preferencia)
            }

            is AddBookFormEvent.ReceberChange -> {
                uiFormState = uiFormState.copy(preferenciaReceber = event.preferencia)
            }

            is AddBookFormEvent.SinopseLivroChange -> {
                uiFormState = uiFormState.copy(sinopse = event.sinopse)
            }

            is AddBookFormEvent.CapaLivroChange -> {
                uiFormState = uiFormState.copy(capaLivro = event.capaLivro)
            }

            is AddBookFormEvent.ImagemLivroChange -> {
                uiFormState = uiFormState.copy(imagemLivro = event.imagemLivro)
            }

            is AddBookFormEvent.Submit -> {
                submitForm()
            }
        }
    }

    private fun submitForm() {
        val validateNameResult = validateUseCase.validateRequiredUseCase(uiFormState.nome)
        val validateGeneroResult = validateUseCase.validateRequiredUseCase(uiFormState.genero)
        val validateIsbnResult = validateUseCase.validateIsbnUseCases(uiFormState.isbn)
        val validateAutorResult = validateUseCase.validateRequiredUseCase(uiFormState.autor)
        val validateEdicaoResult = validateUseCase.validateRequiredUseCase(uiFormState.edicao)
        val validateIdiomaResult = validateUseCase.validateRequiredUseCase(uiFormState.idioma)
        val validateEstadoLivroResult =
            validateUseCase.validateRequiredUseCase(uiFormState.estadoLivro)
        val validateSinopseResult = validateUseCase.validateRequiredUseCase(uiFormState.sinopse)
        val validateCapaLivroResult = validateUseCase.validateRequiredUseCase(uiFormState.capaLivro)
        val validateImagemLivroResult = validateUseCase.validateRequiredUseCase(uiFormState.imagemLivro)

        val hasError = listOf(
            validateNameResult,
            validateGeneroResult,
            validateIsbnResult,
            validateAutorResult,
            validateEdicaoResult,
            validateIdiomaResult,
            validateEstadoLivroResult,
            validateSinopseResult,
            validateCapaLivroResult,
            validateImagemLivroResult
        ).any { !it.isValid }

        if (hasError) {
            uiFormState = uiFormState.copy(
                nomeError = UiText.StringResource(resId = validateNameResult.resId),
                generoError = UiText.StringResource(resId = validateGeneroResult.resId),
                isbnError = UiText.StringResource(resId = validateIsbnResult.resId),
                autorError = UiText.StringResource(resId = validateAutorResult.resId),
                edicaoError = UiText.StringResource(resId = validateEdicaoResult.resId),
                idiomaError = UiText.StringResource(resId = validateIdiomaResult.resId),
                estadoLivroError = UiText.StringResource(resId = validateEstadoLivroResult.resId),
                sinopseError = UiText.StringResource(resId = validateSinopseResult.resId),
                capaLivroError = UiText.StringResource(resId = validateCapaLivroResult.resId),
                imagemLivroError = UiText.StringResource(resId = validateImagemLivroResult.resId)
            )
            return
        }
        addBook()
    }

    private fun addBook() {
        viewModelScope.launch {
            addBookUseCases(uiFormState.toAddBookModel()).collect {
                when (it) {
                    is Resource.Error -> {
                        if (it.code == 409) {
                            uiFormState =
                                uiFormState.copy(generoError = UiText.DynamicText(it.message))
                        } else {
                            requestState = requestState.copy(error = it.message)
                        }

                        requestState = requestState.copy(isLoading = false)
                    }

                    is Resource.Loading -> {
                        requestState = requestState.copy(isLoading = true)
                    }

                    is Resource.Success -> {
                        requestState = requestState.copy(sucess = true, isLoading = false)
                    }

                    is Resource.Finnaly -> {
                        requestState = requestState.copy(isLoading = false)
                    }
                }
            }
        }
    }

    /*private fun consultIsbn(){
        viewModelScope.launch {
            consultIsbnUseCase(uiFormState.isbn).collect {
                when(it) {
                    is Resource.Loading -> {
                        uiFormState = uiFormState.copy(isbnSearchIsLoading = true)
                    }
                    is Resource.Success -> {
                        uiFormState = uiFormState.copy(
                            nome = it.data!!.nome,
                            autor = it.data.autor,
                            genero = it.data.genero,
                            idioma = it.data.idioma,
                            isbnError = UiText.StringResource(R.string.field_valid),
                            isbnSearchIsLoading = false
                        )
                    }
                    is Resource.Error -> {
                        uiFormState = uiFormState.copy(
                            isbnError = UiText.DynamicText(it.message),
                            isbnSearchIsLoading = false
                        )
                    }
                }
            }
        }
    }*/
}