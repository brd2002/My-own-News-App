package com.example.newapp.Screens.Spalsh

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newapp.MainActivity
import com.example.newapp.R
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashScreen : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Splash_screen()
            LaunchedEffect(key1 = true) {
                delay(1800)
                val intent = Intent(this@SplashScreen , MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
@Preview(showBackground = true , showSystemUi = true , name = "splash screen")
@Composable
fun Splash_screen() {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center,
    ){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.splash_screen_logo), contentDescription = "" ,
                modifier = Modifier
                    .size(130.dp)
                    .background(Color.White),
            )
        }

    }
}