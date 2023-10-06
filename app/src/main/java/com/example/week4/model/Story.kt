package com.example.week4.model

import androidx.annotation.DrawableRes

data class Story(
    val name: String,
    @DrawableRes val profilePic: Int,
    val followed: Boolean,
    val followed2: Boolean
) {
}
