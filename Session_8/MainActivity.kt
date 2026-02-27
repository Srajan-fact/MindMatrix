package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {

    val artworks = listOf(
        Artwork(R.drawable.art1, " Bleach", "Studio Pierrot", "2004"),
        Artwork(R.drawable.art2, "Attack on Titan", "Hajime Isayama's", "2013"),
        Artwork(R.drawable.art3, "Game of Thrones", " David Benioff and D. B. Weiss", "2011")
    )

    var currentIndex by remember { mutableStateOf(0) }

    val artwork = artworks[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Image(
                painter = painterResource(id = artwork.imageRes),
                contentDescription = artwork.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(350.dp)
            )
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Text(
                text = artwork.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "${artwork.artist} (${artwork.year})",
                fontSize = 18.sp
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(onClick = {
                currentIndex =
                    if (currentIndex - 1 < 0)
                        artworks.size - 1
                    else
                        currentIndex - 1
            }) {
                Text("Previous")
            }

            Button(onClick = {
                currentIndex =
                    (currentIndex + 1) % artworks.size
            }) {
                Text("Next")
            }
        }
    }
}