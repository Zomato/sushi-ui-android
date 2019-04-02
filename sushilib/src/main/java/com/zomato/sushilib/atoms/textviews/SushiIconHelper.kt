package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.graphics.drawable.Drawable
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes

object SushiIconHelper {
    fun getIconDrawable(
        context: Context,
        icon: String, @ColorRes iconColorRes: Int, @DimenRes iconSize: Int
    ): Drawable = SushiIconDrawable(context).icon(icon).colorRes(iconColorRes).sizeRes(iconSize).apply()


    fun getIconDrawableEditor(context : Context) : SushiIconEditor = SushiIconDrawable(context).editor()
}