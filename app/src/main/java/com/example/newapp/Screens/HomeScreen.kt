package com.example.newapp.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.newapp.Navigation.BottomNavigation
import com.example.newapp.Navigation.allScreen
import com.example.newapp.R

//@Preview(name = "home screen" , showBackground = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: NewsViewModel , navController: NavHostController) {
    var allnews = viewModel.res.value?.news!!
    if (viewModel.res.value == null) {
     Scaffold {
         Column (modifier = Modifier.fillMaxSize()
             .padding(it)
             , verticalArrangement = Arrangement.Center ,
             horizontalAlignment = Alignment.CenterHorizontally){
             CircularProgressIndicator()
         }
     }
    } else {
        Scaffold(
            bottomBar = {
                BottomNavigation(navController = navController)
            }
        ) {

            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(it) ){
                items(allnews) {
                    SingleNews(title = it.title, imageUrl = it.image , onClick = {
                        navController.navigate(allScreen.DetailPageScreen(
                            imageUrl = it.image ,
                            details = it.text
                        ))
                    })
                }
            }
        }
    }

}

@SuppressLint("Range")
//@Preview(showSystemUi = true , showBackground = true)
@Composable
fun SingleNews(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Card(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                model = imageUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onClick()
                    },
                contentScale = ContentScale.Crop,
                contentDescription = null,
                loading = {

                },
                )
        }
        Text(text = title)
    }
}

@Composable
fun Detailpage(modifier: Modifier = Modifier , imageUrl: String , details : String) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        item {
            Card(
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth()
            ) {
                SubcomposeAsyncImage(
                    model = imageUrl,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    loading = {

                    },
                    )
            }
            Text(text = details)
        }
    }
}