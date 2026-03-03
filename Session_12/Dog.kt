package com.example.woof.data

import com.example.woof.R

data class Dog(
    val name: Int,
    val age: Int,
    val hobbies: Int,
    val imageResourceId: Int
)

val dogs = listOf(
    Dog(R.string.dog_name_1, 2, R.string.dog_hobby_1, R.drawable.dog1),
    Dog(R.string.dog_name_2, 3, R.string.dog_hobby_2, R.drawable.dog2),
    Dog(R.string.dog_name_3, 1, R.string.dog_hobby_3, R.drawable.dog3),
    Dog(R.string.dog_name_4, 4, R.string.dog_hobby_4, R.drawable.dog4)
)