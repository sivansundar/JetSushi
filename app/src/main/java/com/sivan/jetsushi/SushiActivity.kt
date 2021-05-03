package com.sivan.jetsushi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sivan.jetsushi.datafactory.SushiItem
import com.sivan.jetsushi.ui.theme.JetSushiTheme

class SushiActivity : ComponentActivity() {

    lateinit var sushiItem : SushiItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sushiItem = intent.getParcelableExtra<SushiItem>("sushi_item")!!

        setContent {
            JetSushiTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting2(sushiItem.name)
                }
            }
        }
    }
}

@Composable
fun Greeting2(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    JetSushiTheme {
        Greeting2("Android")
    }
}