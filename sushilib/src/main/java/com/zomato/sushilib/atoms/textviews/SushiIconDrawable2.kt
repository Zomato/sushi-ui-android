package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.support.annotation.ColorInt
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.theme.ResourceThemeResolver

/**
 * created by championswimmer on 18/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class SushiIconDrawable2 private constructor() : Drawable() {

    private var paint: Paint = Paint()
    private var size = -1
    private var iconChar = ""

    fun setIconSize(sizePx: Int) {
        this.size = sizePx
        setBounds(0, 0, size, size)
        invalidateSelf()
    }

    fun setColor(colorInt: Int) {
        paint.color = colorInt
        invalidateSelf()
    }

    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
        invalidateSelf()
    }

    fun setIconChar(iconChar: String) {
        this.iconChar = iconChar
        invalidateSelf()
    }

    override fun getIntrinsicHeight(): Int {
        return size
    }

    override fun getIntrinsicWidth(): Int {
        return size
    }


    override fun draw(canvas: Canvas) {
        paint.textSize = bounds.height().toFloat()
        val textBounds = Rect()
        val textValue = iconChar
        paint.getTextBounds(textValue, 0, textValue.length, textBounds)
        val textBottom = (bounds.height() - textBounds.height()) / 2f + textBounds.height() - textBounds.bottom
        canvas.drawText(textValue, bounds.width() / 2f, textBottom, paint)
    }

    override fun getOpacity(): Int {
        return when (paint.alpha) {
            255 -> PixelFormat.OPAQUE
            0 -> PixelFormat.TRANSPARENT
            else -> PixelFormat.TRANSLUCENT
        }
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    class Builder(val context: Context) {
        var drawable: SushiIconDrawable2 = SushiIconDrawable2()

        init {
            drawable.apply {
                paint.typeface = ResourcesCompat.getFont(context, R.font.wasabi)
                paint.textAlign = Paint.Align.CENTER
                paint.isUnderlineText = false
                paint.color = ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.textColorPrimary)
                paint.isAntiAlias = true
            }
        }


        fun setIconSizeRes(@DimenRes sizeRes: Int): Builder {
            setIconSize(
                context.resources.getDimensionPixelSize(sizeRes)
            )
            return this
        }

        fun setIconSize(sizePx: Int): Builder {
            drawable.setIconSize(sizePx)
            return this
        }


        fun setColorRes(@ColorRes colorResId: Int): Builder {
            drawable.setColor(
                ContextCompat.getColor(context, colorResId)
            )
            return this
        }

        fun setColor(@ColorInt colorInt: Int): Builder {
            drawable.setColor(colorInt)
            return this
        }

        fun setAlpha(alpha: Float): Builder {
            var a = alpha
            if (a > 1) a = 1f;
            if (a < 0) a = 0f;

            drawable.alpha = (a * 255).toInt()
            return this
        }

        fun setAlpha(alpha: Int): Builder {
            drawable.alpha = alpha
            return this
        }

        fun setIconStringRes(@StringRes resId: Int): Builder {
            setIconChar(
                context.resources.getString(resId)
            )
            return this
        }

        fun setIconChar(iconChar: String): Builder {
            drawable.setIconChar(iconChar)
            return this
        }

        fun build(): SushiIconDrawable2 = drawable
    }
}