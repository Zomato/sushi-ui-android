package com.zomato.sushilib.atoms.textviews


import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes

interface SushiIconEditor {
    fun colorRes(@ColorRes colorRes: Int): SushiIconEditor
    fun colorInt(@ColorInt colorInt: Int): SushiIconEditor
    // removed for now, will be added when goig live
//    fun sizePx(size: Int): SushiIconEditor
//    fun sizeDp(size: Int): SushiIconEditor
    fun sizeRes(@DimenRes size: Int): SushiIconEditor
    fun alpha(alpha: Int): SushiIconEditor
    fun icon(icon: String): SushiIconEditor
    fun apply() : SushiIconDrawable
}