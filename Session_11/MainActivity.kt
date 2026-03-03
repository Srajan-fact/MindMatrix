package com.example.session10app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.session10app.data.DataSource
import com.example.session10app.model.Affirmation
import com.example.session10app.model.Topic

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Session10App()
        }
    }
}

@Composable
fun Session10App() {

    var showGrid by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {

        Column {

            Button(
                onClick = { showGrid = !showGrid },
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    if (showGrid) "Show List" else "Show Grid"
                )
            }

            if (showGrid) {
                CoursesGrid()
            } else {
                AffirmationList()
            }
        }
    }
}

@Composable
fun AffirmationCard(
    affirmation: Affirmation,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column {
            Image(
                painter = painterResource(affirmation.imageRes),
                contentDescription = stringResource(affirmation.textRes),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = stringResource(affirmation.textRes),
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Composable
fun AffirmationList() {
    LazyColumn(
        contentPadding = PaddingValues(8.dp)
    ) {
        items(DataSource.affirmations) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun TopicGridItem(
    topic: Topic,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Row {
            Image(
                painter = painterResource(topic.imageRes),
                contentDescription = stringResource(topic.nameRes),
                modifier = Modifier.size(68.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(
                    text = stringResource(topic.nameRes),
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_grain),
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = topic.courseCount.toString())
                }
            }
        }
    }
}

@Composable
fun CoursesGrid() {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp)
    ) {
        items(DataSource.topics) { topic ->
            TopicGridItem(
                topic = topic,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}