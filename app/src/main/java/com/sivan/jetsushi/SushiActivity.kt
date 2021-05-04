package com.sivan.jetsushi

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sivan.jetsushi.datafactory.SushiItem
import com.sivan.jetsushi.ui.theme.JetSushiTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector

class SushiActivity : ComponentActivity() {

    lateinit var sushiItem : SushiItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lightgrey = Color(0xFFFCFCFC)

        sushiItem = intent.getParcelableExtra<SushiItem>("sushi_item")!!

        setContent {
            JetSushiTheme {
                // A surface container using the 'background' color from the theme
                Column(modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White, lightgrey
                            )
                        )
                    ))  {
                    TopBar()
                }

            }
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun TopBar() {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween


    ) {
        BackButton()
        HeartButton()
    }
}


@Preview(showBackground = true)
@Composable
fun BackButton() {

    val context = LocalContext.current

    Card(modifier = Modifier
        .size(42.dp)
        .background(Color.Transparent)
        .clickable { closeActivity(context) },
        shape = CircleShape) {
        Icon(
            imageVector =  Icons.Rounded.ArrowBack, contentDescription = "Back button",
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .background(Color.White, CircleShape)
                .clip(CircleShape)                       // clip to the circle shape
            // add a border (optional)
        )



    }
}

fun closeActivity(context: Context) {
    (context as Activity).finish()
}

@SuppressLint("UnusedCrossfadeTargetStateParameter")
@Preview(showBackground = true)
@Composable
fun HeartButton() {

    var liked by remember { mutableStateOf(false) }

    var isLiked  = (if (liked) Icons.Rounded.Favorite else Icons.Rounded.FavoriteBorder)

    Card(modifier = Modifier
        .size(42.dp)
        .background(Color.Transparent)
        .clickable { liked = !liked },
        shape = CircleShape) {



        Crossfade(targetState = liked) { liked->
            when(liked) {
                true ->  RoundedIcon(Icons.Default.Favorite)

                false -> RoundedIcon(Icons.Default.FavoriteBorder)

            }
        }


    }
}

@Composable
fun RoundedIcon(imageVector: ImageVector) {
    Icon(
        imageVector =  imageVector, contentDescription = "Like button",
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .background(Color.White, CircleShape)
            .clip(CircleShape)                       // clip to the circle shape
        // add a border (optional)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    val lightgrey = Color(0xFFFCFCFC)

    JetSushiTheme {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White, lightgrey
                    )
                )
            ))  {

                TopBar()
        }
    }
}