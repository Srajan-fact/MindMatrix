
package com.example.superheroes30days.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    val day: Int,
    @StringRes val nameRes: Int,
    @StringRes val descriptionRes: Int,
    @DrawableRes val imageRes: Int
)

