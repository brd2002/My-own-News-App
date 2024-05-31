package com.example.newapp.Screens.Profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.newapp.Navigation.BottomNavigation
import com.example.newapp.Navigation.allScreen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfilePage(modifier: Modifier = Modifier , navController: NavHostController) {
   Scaffold (
       bottomBar = {
           BottomNavigation(navController = navController)
       }
   ){
       Column(modifier = Modifier
           .fillMaxSize()
           .padding(it) ,
           horizontalAlignment = Alignment.CenterHorizontally ,
           verticalArrangement = Arrangement.Center) {
           Button(onClick = {
               val auth = FirebaseAuth.getInstance()
               auth.signOut()
               navController.navigate(allScreen.PhoneNumberInputScreen){
                   popUpTo(allScreen.ProfilePageScreen){
                       inclusive = true
                   }
               }
           }) {
               Text(text = "Logout")
           }
       }
   }
}