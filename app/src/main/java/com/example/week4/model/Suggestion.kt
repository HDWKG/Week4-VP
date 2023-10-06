package com.example.week4.model

import androidx.annotation.DrawableRes

data class Suggestion(
    val name: String,
    @DrawableRes val pp: Int,
    val followed: Boolean,
    val followed2: Boolean
) {
}