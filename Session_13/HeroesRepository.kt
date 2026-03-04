package com.example.superheroes30days.model

import com.example.superheroes30days.R

object HeroesRepository {

    val heroes = List(30) { index ->

        val day = index + 1

        Hero(
            day = day,
            nameRes = when (day % 6) {
                1 -> R.string.hero1
                2 -> R.string.hero2
                3 -> R.string.hero3
                4 -> R.string.hero4
                5 -> R.string.hero5
                else -> R.string.hero6
            },
            descriptionRes = when (day % 6) {
                1 -> R.string.description1
                2 -> R.string.description2
                3 -> R.string.description3
                4 -> R.string.description4
                5 -> R.string.description5
                else -> R.string.description6
            },
            imageRes = when (day % 6) {
                1 -> R.drawable.hero1
                2 -> R.drawable.hero2
                3 -> R.drawable.hero3
                4 -> R.drawable.hero4
                5 -> R.drawable.hero5
                else -> R.drawable.hero6
            }
        )
    }
}