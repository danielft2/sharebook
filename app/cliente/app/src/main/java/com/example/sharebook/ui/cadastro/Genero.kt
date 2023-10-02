package com.example.sharebook.ui.cadastro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.sharebook.R
import com.example.sharebook.ui.theme.Lato
import com.example.sharebook.ui.theme.Shapes
import com.example.sharebook.ui.theme.Typography
import com.example.sharebook.ui.theme.golden
import com.example.sharebook.ui.theme.gray200
import com.example.sharebook.ui.theme.gray500
import com.example.sharebook.ui.theme.green600
import com.example.sharebook.ui.theme.green800
import com.example.sharebook.ui.theme.green900
import com.example.sharebook.ui.theme.lightGolden
import com.example.sharebook.ui.theme.white
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

val genresList = listOf("Aventura", "Fantasia", "Ficção", "Romance", "Suspense", "Terror")


@Composable
fun Genero() {
    var selectedGenres by remember { mutableStateOf(setOf<String>()) }
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
                text = "Seus gêneros favoritos.",
                fontFamily = latoFamily,
                fontWeight = FontWeight.Bold,
                style = Typography.h1,
                color = green800
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Escolha até três dos seus gêneros favoritos para fornecermos uma melhor experiência.",
                style = Typography.body1,
                color = green900
            )

            Spacer(modifier = Modifier.height(32.dp))
            genresList.chunked(2).forEach { genrePair ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    genrePair.forEach { genre ->
                        val isSelected = selectedGenres.contains(genre)
                        Button(
                            onClick = {
                                if (isSelected) {
                                    selectedGenres = selectedGenres - genre
                                } else if (selectedGenres.size < 3) {
                                    selectedGenres = selectedGenres + genre
                                }
                            },
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = if (isSelected) lightGolden else gray200,
                                contentColor = Color.Black
                            ),
                            modifier = Modifier
                                .size(150.dp, 118.dp)
                                .border(
                                    1.dp,
                                    if (isSelected) golden else Color.Transparent,
                                    Shapes.medium
                                )
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxSize()
                            ) {
                                StarIcon(
                                    tint = if (isSelected) lightGolden else white,
                                    circleColor = if (isSelected) golden else gray500
                                )
                                Spacer(modifier = Modifier.height(8.dp)) // Adiciona um espaço entre a estrela e o texto
                                Text(text = genre)
                            }
                        }
                    }
                }

            }
            Spacer(modifier = Modifier.height(18.dp))

            // ** Botões de navegar para outras páginas **
            Button(
                onClick = {
                    // TODO
                },
                shape = Shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(green600, shape = Shapes.medium)
                    .height(48.dp)
                    .padding(0.dp),
            ) {
                Text(
                    text = "Confirmar",
                    color = white,
                    fontFamily = Lato,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(0.dp)
                        .background(Color.Transparent)
                )
            }
        }
    }
}

@Composable
fun StarIcon(tint: Color, circleColor: Color) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(circleColor, CircleShape)
        )
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GeneroPreview() {
    Genero()
}
