package com.sivan.jetsushi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sivan.jetsushi.datafactory.DataFactory
import com.sivan.jetsushi.datafactory.SushiCategory
import com.sivan.jetsushi.datafactory.SushiItem
import com.sivan.jetsushi.ui.theme.JetSushiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val lightgrey = Color(0xFFFCFCFC)


        super.onCreate(savedInstanceState)
        setContent {
            JetSushiTheme {


                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White, lightgrey
                                )
                            )
                        )) {
                        Header()
                        WelcomeHeader()
                        searchBar()
                        Categories()
                        TopSushi()
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()

    ) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = null,
            modifier = Modifier.clip(CircleShape))
    }
}

@Composable
fun WelcomeHeader() {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "\uD83D\uDC4B Hi, Angel!",
                modifier = Modifier.padding(24.dp, 0.dp),
                fontSize = 24.sp)

            Text(text = "What is your favourite sushi?",
                modifier = Modifier.padding(24.dp, 16.dp),
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold)
        }

}

@Composable
fun searchBar() {
    val lightgrey = Color(0xFFF1F3FA)
    val textState = remember { mutableStateOf("")}
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent))
    }

}

@Composable
fun Categories() {
    val sushiCategories = DataFactory().getSushiCategory()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp, 0.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                text = "Categories",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp)
            Text(
                text = "See all",
                fontSize = 14.sp,
                modifier = Modifier.align(alignment = Alignment.CenterVertically))

        }

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            items(items = sushiCategories, itemContent = {item ->
                CategoryItem(item)
                Spacer(modifier = Modifier.width(24.dp))

            })

        }


    }
}

@Composable
fun TopSushi() {
    val sushis = DataFactory().getSampleSushis()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(24.dp, 0.dp)) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                text = "Top Sushi",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp)
            Text(
                text = "See all",
                fontSize = 14.sp,
                modifier = Modifier.align(alignment = Alignment.CenterVertically))

        }

        LazyRow(modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 24.dp),
            horizontalArrangement = Arrangement.SpaceEvenly) {

            items(items = sushis, itemContent = {item ->
                SushiItem(item)
                Spacer(modifier = Modifier.width(12.dp))

            })

        }




    }
}

@Preview(showBackground = true)
@Composable
fun CategoriesPreview(){
    Categories()
}

@Preview(showBackground = true)
@Composable
fun TopSushiPreview(){
    TopSushi()
}

@Preview(showBackground = true)
@Composable
fun SushiItemPreview() {
    val sushis = DataFactory().getSampleSushis()

    SushiItem(sushis[0])

}
@Composable
fun SushiItem(item: SushiItem) {

    val context = LocalContext.current

    Card(modifier = Modifier
        .width(180.dp)
        .height(250.dp)
        .clickable {
            context.startActivity(Intent(context, SushiActivity::class.java)
                .putExtra("sushi_item", item))
        }
        .clip(shape = RoundedCornerShape(18.dp)),
        elevation = 12.dp,


    ) {

            
            //Spacer(modifier = Modifier.padding(0.dp, 8.dp))
          Image(
              painter = painterResource(item.image),
            contentDescription = "avatar",

            contentScale = ContentScale.Crop)


        Column(
            modifier = Modifier.background(
                Brush.verticalGradient(
                    listOf(Color.Transparent, Color.Black),
                    startY = 0f,
                    endY = 600f
                )
            )
        ) {

            Spacer(modifier = Modifier.requiredHeight(120.dp))
            Text(text = item.name,
                modifier = Modifier.padding(24.dp, 0.dp),
                fontWeight = FontWeight.Bold,
                color = Color.White,
                fontSize = 16.sp)
            Spacer(modifier = Modifier.padding(0.dp, 2.dp))

            Text(text = item.combo,
                modifier = Modifier.padding(24.dp, 0.dp),
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                fontSize = 12.sp)
            Spacer(modifier = Modifier.padding(0.dp, 4.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                CurrencyText(item.base_price)

                //OrderNow()
            }
        }





    }

}


@Composable
fun CurrencyText(
    basePrice: Double) {
    val textColor = MaterialTheme.colors.surface

    val price = basePrice.toString().split(".")

    Row(modifier = Modifier.padding(24.dp, 0.dp)) {
        Text(text = "$",
            color = textColor,
            fontSize = 12.sp)

        Spacer(modifier = Modifier.padding(1.dp, 4.dp))

        Text(text = price[0],
            color = textColor,
            fontSize = 24.sp)

        Spacer(modifier = Modifier.padding(1.dp, 4.dp))

        Text(text = ".${price[1]}0",
            color = textColor,
            fontSize = 12.sp)
    }
}


@Preview(showBackground = true)
@Composable
fun CurrencyTextPreview() {
    val item = DataFactory().getSushi(1)

    CurrencyText(item.base_price)
}



@Composable
fun CategoryItem(item: SushiCategory) {


        Column(modifier = Modifier.wrapContentSize()) {
           Card(modifier = Modifier
               .wrapContentSize()
               .background(Color.Transparent),
           shape = CircleShape) {
               Image(
                   painter = painterResource(item.imageId),
                   contentDescription = "avatar",
                   contentScale = ContentScale.FillBounds,            // crop the image if it's not a square
                   modifier = Modifier
                       .size(48.dp)
                       .padding(10.dp)
                       .background(Color.White, CircleShape)
                       .clip(CircleShape)                       // clip to the circle shape
                   // add a border (optional)
               )


           }

            Spacer(modifier = Modifier.height(12.dp))

            Text(text = item.name,
                modifier = Modifier.align(CenterHorizontally),
                textAlign = TextAlign.Center,
                 fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
        }


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetSushiTheme {
        val lightgrey = Color(0xFFFCFCFC)

        Surface(color = MaterialTheme.colors.background) {
            Column(modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.White, lightgrey
                        )
                    )
                ))  {
                Header()
                WelcomeHeader()
                searchBar()
                Categories()
                TopSushi()
            }

        }
    }
}