package com.example.session10app.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Affirmation(
    @StringRes val textRes: Int,
    @DrawableRes val imageRes: Int
)