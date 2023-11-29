package com.example.sharebook.auth_feature.presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sharebook.R
import com.example.sharebook.auth_feature.data.remote.response.toUserModel
import com.example.sharebook.auth_feature.domain.usecase.RegisterUseCase
import com.example.sharebook.auth_feature.domain.usecase.validations.ValidateUseCases
import com.example.sharebook.auth_feature.presentation.register.event.RegisterFormEvent
import com.example.sharebook.auth_feature.presentation.register.state.RegisterFormState
import com.example.sharebook.auth_feature.presentation.register.state.RegisterRequestState
import com.example.sharebook.auth_feature.presentation.register.state.toRegisterModel
import com.example.sharebook.core.domain.adapter.TokenStorageManagement
import com.example.sharebook.core.domain.adapter.UserStorageManagement
import com.example.sharebook.core.domain.usecase.ConsultCepUseCase
import com.example.sharebook.core.utils.Resource
import com.example.sharebook.core.utils.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val consultCepUseCase: ConsultCepUseCase,
    private val validateUseCases: ValidateUseCases,
    private val userStorageManagement: UserStorageManagement,
    private val tokenStorageManagement: TokenStorageManagement
) : ViewModel()
{
    var uiFormState by mutableStateOf(RegisterFormState())
        private set

    var requestState by mutableStateOf(RegisterRequestState())
        private set

    fun onEvent(event: RegisterFormEvent) {
        when(event) {
            is RegisterFormEvent.EmailChanged -> {
                uiFormState = uiFormState.copy(email = event.email)
            }
            is RegisterFormEvent.PasswordChanged -> {
                uiFormState = uiFormState.copy(password = event.password)
            }
            is RegisterFormEvent.ConfirmPasswordChange -> {
                uiFormState = uiFormState.copy(confirmPassword = event.confirmPasswordChange)
            }
            is RegisterFormEvent.CepChange -> {
                uiFormState = uiFormState.copy(cep = event.cep)
                if (uiFormState.cep.length == 8) consultCep()
            }
            is RegisterFormEvent.CidadeChange -> {
                uiFormState = uiFormState.copy(cidade = event.cidadeChange)
            }
            is RegisterFormEvent.EstadoChange -> {
                uiFormState = uiFormState.copy(estado = event.estadoChange)
            }
            is RegisterFormEvent.NomeChange -> {
                uiFormState = uiFormState.copy(nome = event.nome)
            }
            is RegisterFormEvent.TelefoneChange -> {
                uiFormState = uiFormState.copy(telefone = event.telefone)
            }
            is RegisterFormEvent.Submit -> { submitForm() }
        }
    }

    private fun submitForm() {
        val validateNameResult = validateUseCases.validateRequiredUseCase(uiFormState.nome)
        val validatePhoneResult = validateUseCases.validatePhoneUseCase(uiFormState.telefone)
        val validateEmailResult = validateUseCases.validateEmailUseCase(uiFormState.email)
        val validatePasswordResult = validateUseCases.validatePasswordUseCase(uiFormState.password)
        val validateConfirmPassowordResult = validateUseCases.validateConfirmPasswordUseCase(
            uiFormState.password,
            uiFormState.confirmPassword
        )
        val validateCepResult = validateUseCases.validateCepUseCase(uiFormState.cep)

        val hasError = listOf(
            validateNameResult,
            validatePhoneResult,
            validateEmailResult,
            validatePasswordResult,
            validateConfirmPassowordResult,
            validateCepResult
        ).any { !it.isValid }

        if (hasError) {
            uiFormState = uiFormState.copy(
                nomeError = UiText.StringResource(resId = validateNameResult.resId),
                telefoneError = UiText.StringResource(resId = validatePhoneResult.resId),
                emailError = UiText.StringResource(resId = validateEmailResult.resId),
                passwordError = UiText.StringResource(resId = validatePasswordResult.resId),
                confirmPasswordError = UiText.StringResource(resId = validateConfirmPassowordResult.resId),
                cepError = UiText.StringResource(resId = validateCepResult.resId)
            )
            return
        }

        register()
    }

    private fun consultCep() {
        viewModelScope.launch {
            consultCepUseCase(uiFormState.cep).collect {
                when (it) {
                    is Resource.Loading -> {
                        uiFormState = uiFormState.copy(cepSearchIsLoading = true)
                    }
                    is Resource.Success -> {
                        uiFormState = uiFormState.copy(
                            cidade = it.data!!.localidade,
                            estado = it.data.uf,
                            cepError = UiText.StringResource(R.string.field_valid)
                        )
                    }
                    is Resource.Error -> {
                        uiFormState = uiFormState.copy(cepError = UiText.DynamicString(it.message!!))
                    }
                    is Resource.Finnaly -> {
                        uiFormState = uiFormState.copy(cepSearchIsLoading = false)
                    }
                }
            }
        }
    }

    private fun register() {
        viewModelScope.launch {
            registerUseCase(uiFormState.toRegisterModel()).collect {
                when (it) {
                    is Resource.Error -> {
                        if (it.code == 409) {
                            uiFormState = uiFormState.copy(telefoneError = UiText.DynamicString(it.message!!))
                        } else {
                            requestState = requestState.copy(error = it.message)
                        }
                    }
                    is Resource.Loading -> {
                        requestState = requestState.copy(isLoading = true)
                    }
                    is Resource.Success -> {
                        requestState = requestState.copy(sucess = true)
                        userStorageManagement.saved(it.data!!.toUserModel())
                        tokenStorageManagement.saved(it.data.accessToken)
                    }
                    is Resource.Finnaly -> {
                        requestState = requestState.copy(isLoading = false)
                    }
                }
            }
        }
    }
}