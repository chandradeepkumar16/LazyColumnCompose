package com.example.mymovieapp.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.mymovieapp.model.getMovies

@Preview
@Composable
fun MovieRow(movie:com.example.mymovieapp.model.Movie = getMovies()[0],
             onItemClick: (String) -> Unit = {}){

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
//        .height(130.dp)
        .clickable {
            onItemClick(movie.id)

        },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
    ) {

        Row(verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                shadowElevation = 5.dp) {
                Image(painter = rememberImagePainter(data = movie.images[0],
                builder = {
                    crossfade(true)
                    transformations(CircleCropTransformation())
                }), contentDescription = "Movie Poster")

            //           Icon(imageVector = Icons.Default.AccountBox,
            //           contentDescription = "This is a movie image")

            }

            Column(modifier =Modifier.padding(4.dp) ) {
                Text(text = movie.title,
                style = MaterialTheme.typography.bodyLarge)
                Text(text = "Directior :${movie.director}",
                style = MaterialTheme.typography.labelSmall)
                Text(text = "Released :${movie.year}",
                    style = MaterialTheme.typography.labelSmall)

                AnimatedVisibility(visible = expanded) {

                Column {
                    Text(buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Color.DarkGray,
                        fontSize = 13.sp)){
                            append("Plot: ")
                        }
                        withStyle(style = SpanStyle(color=Color.DarkGray,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold
                        )){
                            append(movie.plot)
                        }
                    }, modifier = Modifier.padding(6.dp))
                    
                    Divider(modifier = Modifier.padding(6.dp))
                    Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.labelSmall)
                    Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.labelSmall)
                    Text(text = "Rating: ${movie.rating}", style = MaterialTheme.typography.labelSmall)


                }
                }
                Icon(imageVector = if(expanded )Icons.Filled.KeyboardArrowUp else
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded = !expanded
                        },
                        tint = Color.DarkGray)
            }
        }

    }

}
