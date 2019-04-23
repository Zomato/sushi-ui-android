package com.zomato.sushilib.atoms.menu

import android.graphics.drawable.Drawable
import android.support.v4.app.Fragment
import android.view.Menu

data class SushiMenuItem(
    val groupId: Int = Menu.NONE,
    val itemId: Int,
    val order: Int = Menu.NONE,
    val title: String,
    val drawableId: Int,
    val drawable: Drawable? = null,
    val fragment: Fragment?
)
