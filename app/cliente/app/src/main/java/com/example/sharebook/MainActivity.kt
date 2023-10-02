package com.example.sharebook


import Login
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.sharebook.ui.theme.SharebookTheme
import com.example.sharebook.ui.theme.gray200
import com.example.sharebook.ui.theme.green900



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SharebookTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Login()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
   Column(modifier = Modifier
       .fillMaxSize()
       .background(gray200)) {
       Text(
           text = "Criar nova conta.",
           color = green900,
           style = MaterialTheme.typography.h1
       )
   }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SharebookTheme {
        Greeting()
    }
}