package com.zomato.sushilib.utils.graphics

import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView

/**
 * created by championswimmer on 28/03/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
object BitmapUtils {
    @JvmStatic
    fun drawableToBitmap(drawable: Drawable): Bitmap {

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
    fun getShaderForBitmap(
        bmp: Bitmap, view: View, scaleType: ImageView.ScaleType
    ): BitmapShader {

        // The background can only be CENTER_CROP or CENTER_INSIDE
        if (scaleType != ImageView.ScaleType.CENTER_CROP && scaleType != ImageView.ScaleType.CENTER_INSIDE) {
            throw IllegalArgumentException(
                """|ScaleType $scaleType supported.
                   |Use ScaleType.CENTER_CROP or ScaleType.CENTER_INSIDE"""
                    .trimMargin()
            )
        }

        val shader = BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

        var scale = 0f
        var dx = 0f
        var dy = 0f

        view.run {
            if (scaleType == ImageView.ScaleType.CENTER_CROP) {
                if (bmp.width * height > width * bmp.height) {
                    scale = height / bmp.height.toFloat()
                    dx = (width - bmp.width * scale) * 0.5f
                } else {
                    scale = width / bmp.width.toFloat()
                    dy = (height - bmp.height * scale) * 0.5f
                }
            }

            if (scaleType == ImageView.ScaleType.CENTER_INSIDE) {
                if (bmp.width * height < width * bmp.height) {
                    scale = height / bmp.height.toFloat()
                    dx = (width - bmp.width * scale) * 0.5f
                } else {
                    scale = width / bmp.width.toFloat()
                    dy = (height - bmp.height * scale) * 0.5f

                }
            }


            shader.setLocalMatrix(Matrix().apply {
                setScale(scale, scale)
                postTranslate(dx, dy)
            })
        }

        return shader
    }
}