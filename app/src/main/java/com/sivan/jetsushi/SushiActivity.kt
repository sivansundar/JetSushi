package com.sivan.jetsushi

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.rounded.*
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.sivan.jetsushi.datafactory.DataFactory
import com.sivan.jetsushi.datafactory.SushiCategory
import com.sivan.jetsushi.util.splitToWholeAndFraction


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
                    TopBar(sushiItem)
                    ItemHeader(sushiItem)

                }

            }
            }
        }
    }

@Composable
fun TopBar(sushiItem: SushiItem) {


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
fun ItemHeader(sushiItem: SushiItem) {

    val categoryIDs = sushiItem.category
    val categoryList = DataFactory().getSushiCategoryByIDAsList(categoryIDs)

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Sushi Rolls",
        modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
        )
        
        Text(text = "Salmon Category",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 12.sp)

        Spacer(modifier = Modifier.height(18.dp))

        val range = 1..5
        Row(modifier = Modifier
            .wrapContentSize()
            .align(alignment = Alignment.CenterHorizontally)
        ) {
            range.toList().forEach { value ->
                RatingItem(value, sushiItem.rating)
            }
        }


        CategoryView(categoryList)

        SushiImage(sushiItem)

        QuantitySection()
        
        
    }
}

@Composable
fun QuantitySection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Choose the quantity :",
            modifier = Modifier.align(alignment = Alignment.CenterHorizontally),
            fontSize = 16.sp)
    }

    Spacer(modifier = Modifier.height(24.dp))
    

    Row(modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.SpaceEvenly) {

        var liked by remember { mutableStateOf(false) }


        QuantityItem(6)
        QuantityItem(12)
        QuantityItem(24)

    }

    OrderNow()

}

@Preview(showBackground = true)
@Composable
fun OrderNowItemPreview() {
    OrderNow()
}

@Composable
fun OrderNow() {
    val textColor = MaterialTheme.colors.primarySurface

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(24.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.wrapContentSize(),
            verticalArrangement = Arrangement.Center){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        CurrencyTextBig(basePrice = 6.0)
                        OrderNowBig()
                    }
        }


    }
}

@Composable
fun OrderNowBig() {

        Card(shape = CircleShape) {
            Surface(modifier = Modifier.wrapContentSize(), color = Color.Black) {
                Row( modifier = Modifier
                    .wrapContentSize()
                    .padding(14.dp)) {
                    Text(
                        text = "Place order",
                        color = MaterialTheme.colors.surface
                    )

                    Icon(imageVector = Icons.Rounded.ShoppingCart, tint = MaterialTheme.colors.surface,
                        contentDescription = "place order icon",
                        modifier = Modifier.align(Alignment.CenterVertically))
                }
            }

        }


    }

@Composable
fun CurrencyTextBig(
    basePrice: Double) {

    val price = basePrice.toString().split(".")


        Column(modifier = Modifier.wrapContentSize()) {
            Row(modifier = Modifier.padding(24.dp, 0.dp)) {
                Text(text = "$",
                    fontSize = 18.sp)

                Spacer(modifier = Modifier.padding(1.dp, 4.dp))

                Text(text = price[0],
                    fontSize = 28.sp)

                Spacer(modifier = Modifier.padding(1.dp, 4.dp))

                Text(text = ".${price[1]}0",
                    fontSize = 18.sp)

            }

            Text(text = "Total Price",
                fontSize = 12.sp,
                modifier = Modifier
                    .wrapContentWidth()
                    .align(alignment = Alignment.CenterHorizontally))

    }



}

@Preview(showBackground = true)
@Composable
fun QuantitySectionPreview() {
    QuantitySection()
}

@Preview(showBackground = true)
@Composable
fun QuantityItemPreview() {
    QuantityItem(6)
}


@Composable
fun QuantityItem(quantity: Int) {

    var isSelected by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.padding(8.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(16.dp),
        color = when(isSelected) {
         true -> MaterialTheme.colors.primary
         false -> MaterialTheme.colors.background
        }
    ) {
        Row(modifier = Modifier
            .toggleable(
                value = isSelected,
                onValueChange = {
                    isSelected = !isSelected
                }
            )) {
            Text(
                text = "$quantity units",
                style = MaterialTheme.typography.body2,
                modifier = Modifier.padding(12.dp)
            )
        }
    }
}


@Composable
fun CategoryView(categoryList: ArrayList<SushiCategory>) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 24.dp),
        horizontalArrangement = Arrangement.SpaceEvenly) {


        items(items = categoryList, itemContent = {item ->
            CategoryItem(item)
        })
    }
}

@Composable
fun SushiImage(sushiItem: SushiItem) {
    Row (modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center){
        SushiImageCard(sushiItem)
    }
}

@Composable
fun SushiImageCard(sushiItem: SushiItem) {

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .padding(24.dp),
        elevation = 12.dp,
        shape = RoundedCornerShape(18.dp)
        ) {

        Image(painter = painterResource(id = sushiItem.image),
            contentDescription = "sushi image",
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Crop

        )

    }
}


@Preview(showBackground = true)
@Composable
fun ItemHeaderPreview() {
    val item = DataFactory().getSushi(0)
    ItemHeader(item)
}

@Preview(showBackground = true)
@Composable
fun RatingItemPreview() {
    val item = DataFactory().getSushi(1)

    RatingItem(3, item.rating)
}

@Composable
fun RatingItem(value: Int, rating: Float) {
    val (whole, fraction) = rating.splitToWholeAndFraction()
    val ratingColor = Color(0xFF1E283D)


    val imageVector = if (value.toFloat() == whole + 1 && fraction in 0.5..0.9){
        Icons.Rounded.StarHalf
    } else {
        Icons.Rounded.Star
    }

    val color = if (value <= whole || value.toFloat() == whole +1 && fraction in 0.5..0.9) {
        ratingColor
    } else {
        Color.Gray
    }

    Icon(imageVector = imageVector, contentDescription = "Rating item" , tint = color)
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
    val item = DataFactory().getSushi(1)

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

                TopBar(item)
                ItemHeader(item)
        }
    }
}