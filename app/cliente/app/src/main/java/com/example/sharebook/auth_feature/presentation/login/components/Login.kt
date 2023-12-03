package com.example.sharebook.auth_feature.presentation.login.components
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.sharebook.R
import com.example.sharebook.auth_feature.presentation.login.LoginViewModel
import com.example.sharebook.auth_feature.presentation.login.event.LoginFormEvent
import com.example.sharebook.core.presentation.components.*
import com.example.sharebook.core.presentation.components.button.ButtonPrimary
import com.example.sharebook.core.presentation.components.input.TextFieldCustom
import com.example.sharebook.core.presentation.navigation.routes.unauthenticated.PublicRoutes
import com.example.sharebook.core.presentation.ui.theme.*
import com.example.sharebook.core.utils.UiText

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Login(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
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
                .padding(20.dp),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Header { navController.popBackStack() }

                Spacer(modifier = Modifier.height(32.dp))

                Image(
                    painter = painterResource(id = R.drawable.ilus_login),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .width(244.dp)
                        .height(124.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = UiText.StringResource(R.string.login_title).asString(),
                fontFamily = Lato,
                fontWeight = FontWeight.Bold,
                style = Typography.h1,
                color = green800
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = UiText.StringResource(R.string.login_subtitle).asString(),
                fontWeight = FontWeight.Medium,
                fontFamily = Inter,
                fontSize = 14.sp,
                lineHeight = 21.sp,
                color = gray500
            )

            Spacer(modifier = Modifier.height(32.dp))

            TextFieldCustom(
                label = UiText.StringResource(R.string.field_email).asString(),
                value = viewModel.uiFormState.email,
                errorMessage = viewModel.uiFormState.emailError.asString(),
                onChange = { viewModel.onEvent(LoginFormEvent.EmailChanged(it)) },
            )

            Spacer(modifier = Modifier.height(12.dp))

            TextFieldCustom(
                label = UiText.StringResource(R.string.field_password).asString(),
                value = viewModel.uiFormState.password,
                errorMessage = viewModel.uiFormState.passwordError.asString(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation(),
                onChange = { viewModel.onEvent(LoginFormEvent.PasswordChanged(it)) },
            )

            Spacer(modifier = Modifier.height(12.dp))
            
            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false,
            ) {
                TextButton(
                    onClick = {  },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.defaultMinSize(minHeight = 1.dp)
                ){
                    Text(
                        text = UiText.StringResource(R.string.login_forgot_password).asString(),
                        color = green700,
                        style = Typography.subtitle1
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            ButtonPrimary(
                text = UiText.StringResource(R.string.login_submit).asString(),
                loading = viewModel.uiRequestState.isLoading,
                onClick = { viewModel.onEvent(LoginFormEvent.Submit) }
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
                        text = UiText.StringResource(R.string.login_have_account).asString(),
                        fontFamily = Inter,
                        fontWeight = FontWeight.Medium,
                        color = gray500,
                        style = TextStyle(letterSpacing = 0.sp),
                        fontSize = 14.sp,
                        modifier = Modifier.padding(end = 2.dp)
                    )
                    TextButton(
                        onClick = { navController.navigate(PublicRoutes.RegisterScreen.route) },
                        contentPadding = PaddingValues(0.dp),
                        modifier = Modifier.defaultMinSize(minHeight = 1.dp)
                    ){
                        Text(
                            text = UiText.StringResource(R.string.login_register_action).asString(),
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

        if (!viewModel.uiRequestState.error.isNullOrEmpty()) {
            Toast.makeText(LocalContext.current, viewModel.uiRequestState.error, Toast.LENGTH_SHORT).show()
        }
    }
}