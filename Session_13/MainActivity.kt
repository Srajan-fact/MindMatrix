package com.example.superheroes30days

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.superheroes30days.model.HeroesRepository
import com.example.superheroes30days.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SuperheroesTheme {
                SuperheroesApp()
            }
        }
    }
}

@Composable
fun SuperheroesApp() {

    Scaffold(
        topBar = { TopBar() }
    ) { padding ->

        HeroesList(
            heroes = HeroesRepository.heroes,
            modifier = Modifier.padding(padding)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {

    TopAppBar(
        title = {
            Text(
                text = "30 Days of Superheroes",
                style = MaterialTheme.typography.displayLarge
            )
        }
    )
}