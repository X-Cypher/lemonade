
package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonApp(){

    var currStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0)}

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                Text(
                    text = "Lemonade",
                    fontWeight = FontWeight.Bold,
                )
            }, colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = Color(
                    red = 100,
                    green = 91,
                    blue = 24,
                    alpha = 255
                )
                )
            )
        },
    )
    { innerPadding ->  Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        color = MaterialTheme.colorScheme.background
    )
    {

    }
        when(currStep){
            1 -> LemonImageAndText(
                image = R.drawable.lemon_tree,
                text = R.string.tap_tree,
                imageDescription = R.string.lemon_tree,
                onImageClick = {
                    currStep = 2
                    squeezeCount = (2..5).random()
                }
            )

            2 -> LemonImageAndText(
                image = R.drawable.lemon_squeeze,
                text = R.string.keep_tapping,
                imageDescription = R.string.lemon,
                onImageClick = {
                    squeezeCount--
                    if(squeezeCount == 0){
                        currStep = 3
                    }
                }
            )

            3 -> LemonImageAndText(
                image = R.drawable.lemon_drink,
                text = R.string.tap,
                imageDescription = R.string.glass_lemonade,
                onImageClick = {
                    currStep = 4
                }
            )

            4 -> LemonImageAndText(
                image = R.drawable.lemon_restart,
                text = R.string.tap_glass,
                imageDescription = R.string.empty_glass,
                onImageClick = {
                    currStep = 1
                }
            )
        }
    }

}

@Composable
fun LemonImageAndText(image : Int, text: Int, imageDescription: Int, onImageClick: () -> Unit){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    )
    {
        Image(painter = painterResource(id = image),
            contentDescription = stringResource(id = imageDescription),
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = Color(red = 165, green = 205, blue = 196),
                    shape = RoundedCornerShape(18.dp)
                )
                .clickable(onClick = onImageClick)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(id = text), fontSize = 18.sp)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    LemonadeTheme {
        LemonApp()
    }
}