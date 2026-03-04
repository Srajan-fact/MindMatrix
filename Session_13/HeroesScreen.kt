package com.example.superheroes30days

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.superheroes30days.model.Hero

@Composable
fun HeroesList(
    heroes: List<Hero>,
    modifier: Modifier = Modifier
) {

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(heroes) { hero ->
            HeroCard(hero)
        }
    }
}

@Composable
fun HeroCard(hero: Hero) {

    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        Row(
            modifier = Modifier
                .padding(16.dp)
                .height(72.dp)
        ) {

            Image(
                painter = painterResource(hero.imageRes),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Column {

                Text(
                    text = "Day ${hero.day}",
                    style = MaterialTheme.typography.displayMedium
                )

                Text(
                    text = stringResource(hero.nameRes),
                    style = MaterialTheme.typography.displaySmall
                )

                Text(
                    text = stringResource(hero.descriptionRes),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}