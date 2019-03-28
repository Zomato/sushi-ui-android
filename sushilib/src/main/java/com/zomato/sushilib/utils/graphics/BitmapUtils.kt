package com.zomato.sushilib.utils.graphics

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View

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

    @JvmStatic
    fun getShaderForBitmap(bmp: Bitmap, view: View): BitmapShader {
        val shader = BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        var scale = 0f
        var dx = 0f
        var dy = 0f

        view.run {
            // algorithm for CENTER_CROP scaling

            if (bmp.width * height > width * bmp.height) {
                scale = height / bmp.height.toFloat()
                dx = (width - bmp.width * scale) * 0.5f
            } else {
                scale = width / bmp.width.toFloat()
                dy = (height - bmp.height * scale) * 0.5f
            }

            // algorithm for CENTER_INSIDE scaling

//            if (bmp.width * height < width * bmp.height) {
//                scale = height / bmp.height.toFloat()
//                dx = (width - bmp.width * scale) * 0.5f
//            } else {
//                scale = width / bmp.width.toFloat()
//                dy = (height - bmp.height * scale) * 0.5f
//            }

            shader.setLocalMatrix(Matrix().apply {
                setScale(scale, scale)
                postTranslate(dx, dy)
            })
        }

        return shader
    }
}