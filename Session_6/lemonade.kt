package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonApp()
            }
        }
    }
}

@Composable
fun LemonApp() {

    // Current UI step (1-4)
    var currentStep by remember { mutableStateOf(1) }

    // Number of squeezes required
    var squeezeCount by remember { mutableStateOf(0) }

    // Remaining squeezes
    var squeezeTarget by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        when (currentStep) {

            // STEP 1: Select Lemon
            1 -> {
                LemonTextAndImage(
                    textLabel = R.string.lemon_select,
                    imageResource = R.drawable.lemon_tree,
                    contentDescription = R.string.lemon_tree_content_description,
                    onImageClick = {
                        currentStep = 2
                        squeezeTarget = Random.nextInt(2, 5)
                        squeezeCount = 0
                    }
                )
            }

            // STEP 2: Squeeze Lemon
            2 -> {
                LemonTextAndImage(
                    textLabel = R.string.lemon_squeeze,
                    imageResource = R.drawable.lemon_squeeze,
                    contentDescription = R.string.lemon_content_description,
                    onImageClick = {
                        squeezeCount++
                        if (squeezeCount >= squeezeTarget) {
                            currentStep = 3
                        }
                    }
                )
            }

            // STEP 3: Drink Lemonade
            3 -> {
                LemonTextAndImage(
                    textLabel = R.string.lemon_drink,
                    imageResource = R.drawable.lemon_drink,
                    contentDescription = R.string.lemonade_content_description,
                    onImageClick = {
                        currentStep = 4
                    }
                )
            }

            // STEP 4: Restart
            4 -> {
                LemonTextAndImage(
                    textLabel = R.string.lemon_restart,
                    imageResource = R.drawable.lemon_restart,
                    contentDescription = R.string.empty_glass_content_description,
                    onImageClick = {
                        currentStep = 1
                    }
                )
            }
        }
    }
}

@Composable
fun LemonTextAndImage(
    textLabel: Int,
    imageResource: Int,
    contentDescription: Int,
    onImageClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {

        Text(
            text = stringResource(textLabel),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(contentDescription),
            modifier = Modifier
                .wrapContentSize()
                .border(
                    BorderStroke(
                        2.dp,
                        Color(105, 205, 216)
                    ),
                    shape = RoundedCornerShape(4.dp)
                )
                .clickable { onImageClick() }
                .padding(16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonPreview() {
    LemonadeTheme {
        LemonApp()
    }
}