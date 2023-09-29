import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import com.example.sharebook.ui.theme.gray500
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.createFontFamilyResolver
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.example.sharebook.R
import com.example.sharebook.ui.theme.Lato
import com.example.sharebook.ui.theme.Shapes
import com.example.sharebook.ui.theme.Typography
import com.example.sharebook.ui.theme.background
import com.example.sharebook.ui.theme.gray200
import com.example.sharebook.ui.theme.gray800
import com.example.sharebook.ui.theme.green200
import com.example.sharebook.ui.theme.green500
import com.example.sharebook.ui.theme.green600
import com.example.sharebook.ui.theme.green700
import com.example.sharebook.ui.theme.green800
import com.example.sharebook.ui.theme.green900
import com.example.sharebook.ui.theme.white
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
data class LoginForm(
    val email : String,
    val password: String
)
object RetrofitClient {
    private const val BASE_URL = "http://10.0.2.2:3333/"

    val instance: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

interface ApiService {
    @POST("/login") // Substitua "endpoint_do_seu_post" pelo caminho real do seu endpoint POST
    fun enviarDados(@Body dados: LoginForm): Call<Any> // Substitua "Dados" e "Resposta" pelos seus modelos de dados reais
}

val apiService = RetrofitClient.instance.create(ApiService::class.java)


@Composable
fun Login() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White // Cor de fundo da tela
    ) {
        val latoFamily = FontFamily(
            Font(R.font.lato_bold, FontWeight.Bold),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE6F2F1), // Cor de cima
                            Color(0xFFF8F8F8)  // Cor de baixo
                        ),
                        startY = 0f,
                        endY = 800f
                    )
                )
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.login_ilustration),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(244.dp)
                    .height(124.dp)
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Acesse sua conta.",
                fontFamily = latoFamily,
                fontWeight = FontWeight.Bold,
                style = Typography.h1,
                color = green800
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Informe suas credencias abaixo para acessar, caso não tenha realize o cadastro.",
                style = Typography.body1,
                color = green900
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ******* Formulário de login *******
            // ** Campo de email **
            Text(
                text = "Email",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField (
                value = email,
                onValueChange = {email = it},
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ** Campo de senha **
            Text(
                text = "Senha",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField (
                value = password,
                onValueChange = {password = it},
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            Text(
                text = "Esqueceu sua senha?",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.weight(1f))

            // ** Botões de navegar para outras páginas **
            Button(
                onClick = {
                    val loginForm = LoginForm(email, password)
                    val call = apiService.enviarDados(loginForm)
                    call.enqueue(object : Callback<Any>{
                        override fun onResponse(call: Call<Any>, response: Response<Any>) {
                            if (response.isSuccessful) {
                                val resposta = response.body()
                                println(resposta)
                            } else {
                                // Tratar erros aqui
                                println(response)
                            }
                        }
                        override fun onFailure(call: Call<Any>, t: Throwable) {
                            // Tratar erros de conexão ou outras exceções aqui
                            println("Exceção" + t)
                        }
                    })

                },
                shape = Shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(green600, shape = Shapes.medium)
                    .height(48.dp)
                    .padding(0.dp),
            ){
                Text(
                    text = "Entrar",
                    color = white,
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(0.dp)
                        .background(Color.Transparent)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // ** Texto de navegação para cadastro **
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Não tem uma conta ainda?",
                    fontFamily = Lato,
                    fontWeight = FontWeight.Normal,
                    color = gray500,
                    fontSize = 14.sp
                )
                Text(
                    text = " Cadastre-se",
                    fontFamily = Lato,
                    fontWeight = FontWeight.Normal,
                    color = green700,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    Login()
}
