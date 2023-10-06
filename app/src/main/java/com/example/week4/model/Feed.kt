package com.example.week4.model

import androidx.annotation.DrawableRes

data class Feed(
    val name: String,
    @DrawableRes val pp: Int,
    @DrawableRes val contentPicture: Int,
    val liked: Boolean,
    val saved: Boolean,
    val likes: Int,
    val caption: String,
    val date: String
) {
}