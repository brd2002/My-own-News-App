package com.example.newapp.Screens.Animation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newapp.Navigation.allScreen
import com.example.newapp.R

@Composable
fun SuccessAnimationPage(modifier: Modifier = Modifier, navController: NavHostController) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.suscess_animaiton))
    val progress by animateLottieCompositionAsState(composition)
    LaunchedEffect(progress) {
        if (progress == 1f){
            navController.navigate(allScreen.HomePage){
                popUpTo(allScreen.SuccessAnimatonScreen){
                    inclusive = true
                }
            }
        }
    }
    Box (modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
        contentAlignment = Alignment.Center){
        LottieAnimation(composition = composition ,
            modifier = Modifier.size(250.dp), iterations = Int.MAX_VALUE)
    }
}