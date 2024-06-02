package com.example.newapp.Navigation

import androidx.activity.viewModels
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.newapp.Screens.Animation.SuccessAnimationPage
import com.example.newapp.Screens.Detailpage
import com.example.newapp.Screens.HomeScreen
import com.example.newapp.Screens.NewsViewModel
import com.example.newapp.Screens.Profile.ProfilePage
import com.example.newapp.login.phoneNumberInputScreen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.serialization.Serializable

@Composable
fun  AppNavHost() {
    val auth = FirebaseAuth.getInstance()
    val navController = rememberNavController()
    lateinit var startdes : allScreen
    val viewModel: NewsViewModel  = NewsViewModel()
    if (auth.currentUser !== null){
        startdes = allScreen.HomePage
    }else {
        startdes = allScreen.PhoneNumberInputScreen
    }
    NavHost(navController = navController, startDestination = startdes) {
        composable<allScreen.HomePage> {
            HomeScreen(viewModel = viewModel , navController = navController)
        }
        composable<allScreen.DetailPageScreen> { 
            val obj = it.toRoute<allScreen.DetailPageScreen>()
            Detailpage(imageUrl = obj.imageUrl, details = obj.details)
        }
        composable<allScreen.PhoneNumberInputScreen> {
            phoneNumberInputScreen(navController = navController)
        }
        composable<allScreen.ProfilePageScreen> {
            ProfilePage(navController =  navController)
        }
        composable<allScreen.SuccessAnimatonScreen> { 
            SuccessAnimationPage(navController = navController)
        }

    }

}
sealed class allScreen{
    @Serializable
    object SuccessAnimatonScreen : allScreen()
    @Serializable
    object HomePage : allScreen()
    @Serializable
    data class DetailPageScreen(val imageUrl: String, val details: String) : allScreen()
    @Serializable
    object PhoneNumberInputScreen : allScreen()
    @Serializable
    object ProfilePageScreen : allScreen()
}
@Composable
fun BottomNavigation(navController: NavController) {
    NavigationBar {
        AddItem(
            false, Icons.Default.Home,
            {
                navController.navigate(allScreen.HomePage)
            },
            "Home" ,
            "Home")
        AddItem(
            false, Icons.Filled.Person,
            {
                navController.navigate(allScreen.ProfilePageScreen)
            },
            "Profile" ,
            "Profile")

    }
}
@Composable
fun RowScope.AddItem(
    selected: Boolean = false,
    icons: ImageVector,
    onClick: () -> Unit,
    screenDescription: String,
    screenTitle: String
) {
    var onSelect = selected
    NavigationBarItem(selected = selected, onClick = {
        onSelect = true
        onClick()
    }, label = { Text(text = screenTitle) }, icon = {
        Icon(
            imageVector = icons,
            contentDescription = screenTitle
        )
    },
        alwaysShowLabel = false
    )
}