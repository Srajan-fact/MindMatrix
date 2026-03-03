package com.example.session10app.data

import com.example.session10app.R
import com.example.session10app.model.Affirmation
import com.example.session10app.model.Topic

object DataSource {

    val affirmations = listOf(
        Affirmation(R.string.affirmation1, R.drawable.image1),
        Affirmation(R.string.affirmation2, R.drawable.image2),
        Affirmation(R.string.affirmation3, R.drawable.image3),
        Affirmation(R.string.affirmation4, R.drawable.image4)
    )

    val topics = listOf(
        Topic(R.string.architecture, 58, R.drawable.architecture),
        Topic(R.string.business, 78, R.drawable.business),
        Topic(R.string.design, 423, R.drawable.design),
        Topic(R.string.music, 212, R.drawable.music)
    )
}