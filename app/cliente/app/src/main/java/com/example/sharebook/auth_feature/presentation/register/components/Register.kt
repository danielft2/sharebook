package com.example.sharebook.auth_feature.presentation.register.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.auth_feature.presentation.register.RegisterViewModel
import com.example.sharebook.auth_feature.presentation.register.event.RegisterFormEvent
import com.example.sharebook.core.presentation.components.ButtonPrimary
import com.example.sharebook.core.presentation.components.Header
import com.example.sharebook.core.presentation.components.TextFieldCustom
import com.example.sharebook.core.presentation.navigation.routes.unauthenticated.PublicRoutes
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.UiText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Register(
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController
) {
    Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF0F8F7),
                            Color(0xFFF8F8F8)
                        ),
                        startY = 0f,
                        endY = 500f
                    )
                )
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
        ) {


            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .weight(1f, false)
            ) {
                Header { navController.popBackStack() }

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    text = UiText.StringResource(R.string.register_title).asString(),
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    style = Typography.h1,
                    color = green800
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = UiText.StringResource(R.string.register_subtitle).asString(),
                    style = Typography.body1,
                    color = green900
                )

                Spacer(modifier = Modifier.height(32.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_name).asString(),
                    value = viewModel.uiFormState.nome,
                    errorMessage = viewModel.uiFormState.nomeError.asString(),
                    onChange = { viewModel.onEvent(RegisterFormEvent.NomeChange(it)) }
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_phone).asString(),
                    value = viewModel.uiFormState.telefone,
                    errorMessage = viewModel.uiFormState.telefoneError.asString(),
                    onChange = { viewModel.onEvent(RegisterFormEvent.TelefoneChange(it)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_email).asString(),
                    value = viewModel.uiFormState.email,
                    errorMessage = viewModel.uiFormState.emailError.asString(),
                    onChange = { viewModel.onEvent(RegisterFormEvent.EmailChanged(it)) }
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_password).asString(),
                    value = viewModel.uiFormState.password,
                    visualTransformation = PasswordVisualTransformation(),
                    errorMessage = viewModel.uiFormState.passwordError.asString(),
                    onChange = { viewModel.onEvent(RegisterFormEvent.PasswordChanged(it)) }
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_confirm_password).asString(),
                    value = viewModel.uiFormState.confirmPassword,
                    visualTransformation = PasswordVisualTransformation(),
                    errorMessage = viewModel.uiFormState.confirmPasswordError.asString(),
                    onChange = { viewModel.onEvent(RegisterFormEvent.ConfirmPasswordChange(it)) }
                )

                Spacer(modifier = Modifier.height(18.dp))

                Box(modifier = Modifier.fillMaxWidth()) {
                    TextFieldCustom(
                        label = UiText.StringResource(R.string.field_cep).asString(),
                        value = viewModel.uiFormState.cep,
                        enable = !viewModel.uiFormState.cepSearchIsLoading,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        errorMessage = viewModel.uiFormState.cepError.asString(),
                        onChange = { viewModel.onEvent(RegisterFormEvent.CepChange(it.take(8))) }
                    )
                    if (viewModel.uiFormState.cepSearchIsLoading) {
                        CircularProgressIndicator(
                            color = green500,
                            strokeWidth = 2.dp,
                            modifier = Modifier
                                .width(18.dp)
                                .height(18.dp)
                                .align(Alignment.CenterEnd)
                                .offset(y = 10.dp, x = (-14).dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_state).asString(),
                    value = viewModel.uiFormState.estado,
                    enable = false,
                    errorMessage = viewModel.uiFormState.estadoError.asString(),
                    onChange = { viewModel.onEvent(RegisterFormEvent.EstadoChange(it)) }
                )

                Spacer(modifier = Modifier.height(18.dp))

                TextFieldCustom(
                    label = UiText.StringResource(R.string.field_city).asString(),
                    value = viewModel.uiFormState.cidade,
                    enable = false,
                    errorMessage = viewModel.uiFormState.cidadeError.asString(),
                    onChange = { viewModel.onEvent(RegisterFormEvent.CidadeChange(it)) }
                )

                Spacer(modifier = Modifier.height(100.dp))
            }


            Spacer(modifier = Modifier.height(18.dp))

            ButtonPrimary(
                text = UiText.StringResource(R.string.register_submit).asString(),
                loading = viewModel.requestState.isLoading,
                onClick = { viewModel.onEvent(RegisterFormEvent.Submit) }
            )

            Spacer(modifier = Modifier.height(8.dp))

            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false,
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = UiText.StringResource(R.string.register_alredy_account).asString(),
                        fontFamily = Inter,
                        fontWeight = FontWeight.Medium,
                        color = gray500,
                        style = TextStyle(letterSpacing = 0.sp),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    TextButton(
                        onClick = { navController.navigate(PublicRoutes.LoginScreen.route) },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.defaultMinSize(minHeight = 1.dp)
                    ){
                        Text(
                            text = UiText.StringResource(R.string.register_login_action).asString(),
                            fontFamily = Inter,
                            fontWeight = FontWeight.Medium,
                            style = TextStyle(letterSpacing = 0.sp),
                            color = green500,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }

        if (!viewModel.requestState.error.isNullOrEmpty()) {
            Toast.makeText(LocalContext.current, viewModel.requestState.error, Toast.LENGTH_SHORT).show()
        }
    }
}