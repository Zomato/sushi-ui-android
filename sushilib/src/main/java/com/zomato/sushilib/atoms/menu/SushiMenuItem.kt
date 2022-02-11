package com.zomato.sushilib.atoms.menu

import android.graphics.drawable.Drawable

data class SushiMenuItem(
    val itemId: Int,
    val title: String,
    val drawableId: Int? = null,
    val drawable: Drawable? = null
)
