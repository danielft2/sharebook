package com.example.sharebook.ui.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.sharebook.ui.theme.gray500
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sharebook.R
import com.example.sharebook.ui.theme.Lato
import com.example.sharebook.ui.theme.Shapes
import com.example.sharebook.ui.theme.Typography
import com.example.sharebook.ui.theme.gray200
import com.example.sharebook.ui.theme.green600
import com.example.sharebook.ui.theme.green700
import com.example.sharebook.ui.theme.green800
import com.example.sharebook.ui.theme.green900
import com.example.sharebook.ui.theme.white
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class CadastroForm(
    val nome: String,
    val telefone: String,
    val email: String,
    val senha: String,
    val cep: String,
    val estado: String,
    val cidade: String
) {
//    val genders: Array<String> = emptyArray()
}

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
    @POST("/user") // Substitua "endpoint_do_seu_post" pelo caminho real do seu endpoint POST
    fun cadastroData(@Body data: CadastroForm): Call<Any> // Substitua "Dados" e "Resposta" pelos seus modelos de dados reais
}

val apiService = RetrofitClient.instance.create(ApiService::class.java)

@Composable
fun Cadastro() {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmacao by remember { mutableStateOf("") }
    var cep by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    // val coroutineScope = rememberCoroutineScope()

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
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "Criar nova conta",
                fontFamily = latoFamily,
                fontWeight = FontWeight.Bold,
                style = Typography.h1,
                color = green800
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Informe seus dados abaixo e em seguida verifique sua conta para acessar o aplicativo.",
                style = Typography.body1,
                color = green900
            )

            Spacer(modifier = Modifier.height(32.dp))

            // ******* Formulário de cadastro *******
            // ** Campo de nome **
            Text(
                text = "Nome Completo",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = nome,
                onValueChange = { nome = it },
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ** Campo de telefone **
            Text(
                text = "Telefone de Contato",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = telefone,
                onValueChange = { telefone = it },
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ** Campo de email **
            Text(
                text = "Email",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = email,
                onValueChange = { email = it },
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

            BasicTextField(
                value = senha,
                onValueChange = { senha = it },
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ** Campo de confirmação da senha **
            Text(
                text = "Confirmar Senha",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = confirmacao,
                onValueChange = { confirmacao = it },
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ** Campo de CEP **
            Text(
                text = "CEP",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = cep,
                onValueChange = { cep = it },
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ** Campo de Estado **
            Text(
                text = "Estado",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = estado,
                onValueChange = { estado = it },
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // ** Campo de Cidade **
            Text(
                text = "Cidade",
                color = green800,
                style = Typography.subtitle1
            )

            Spacer(modifier = Modifier.height(4.dp))

            BasicTextField(
                value = cidade,
                onValueChange = { cidade = it },
                modifier = Modifier
                    .background(gray200, shape = Shapes.medium)
                    .fillMaxWidth()
                    .padding(16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // ** Botões de navegar para outras páginas **
            Button(
                onClick = {
                    val cadastroForm =
                        CadastroForm(nome, telefone, email, senha, cep, estado, cidade)
                    var call = apiService.cadastroData(cadastroForm)
                    call.enqueue(object : Callback<Any> {
                        override fun onResponse(call: Call<Any>, response: Response<Any>) {
                            if (response.isSuccessful && cadastroForm.senha == confirmacao) {
                                val resposta = response.body()
                                println(resposta)
                            } else if(cadastroForm.senha != confirmacao){
                                println("As senhas não estão iguais")
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
            ) {
                Text(
                    text = "Criar conta",
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
                    text = "Já tem uma conta?",
                    fontFamily = Lato,
                    fontWeight = FontWeight.Normal,
                    color = gray500,
                    fontSize = 14.sp
                )
                Text(
                    text = " Entre aqui",
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
fun CadastroPreview() {
    Cadastro()
}