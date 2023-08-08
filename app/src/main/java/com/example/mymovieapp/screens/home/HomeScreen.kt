package com.example.mymovieapp.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mymovieapp.model.Movie
import com.example.mymovieapp.model.getMovies
import com.example.mymovieapp.navigation.MovieScreens
import com.example.mymovieapp.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController : NavController){

    Scaffold(topBar = {
        Row(horizontalArrangement = Arrangement.Start , modifier = Modifier.height(30.dp) ) {

            Text(text = "Movies")
        }
    }) {
        Spacer(modifier = Modifier.width(200.dp).height(200.dp))
        MainContent(navController=navController)
    }
}



@Composable
fun MainContent(navController: NavController,movieList: List<Movie> = getMovies()){
    Column(modifier = Modifier.padding(12.dp)) {

        LazyColumn {
            items(items =movieList){
                MovieRow(movie = it){movie ->
                    navController.navigate(route = MovieScreens.DetailScreen.name+"/$movie")

                }
            }
        }
    }

}
