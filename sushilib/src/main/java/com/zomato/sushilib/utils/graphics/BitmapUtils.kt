package com.zomato.sushilib.utils.graphics

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable

/**
 * created by championswimmer on 28/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
object BitmapUtils {
    @JvmStatic
    fun drawableToBitmap(drawable: Drawable?): Bitmap? {
        if (drawable == null) return null

        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bmp = Bitmap.createBitmap(
            drawable.intrinsicWidth, drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bmp)
        drawable.bounds = Rect(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bmp
    }
}