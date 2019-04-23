package com.zomato.sushilib.atoms.drawables

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
 * A Drawable that uses Sushi's Wasabi fonticon
 * to create icons based on icon characters
 *
 * created by championswimmer on 18/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class SushiIconDrawable private constructor() : Drawable() {

    private var paint: Paint = Paint()
    private var size = -1
    private var iconChar = ""

    /**
     * Set size in pixels
     *
     * @param sizePx
     */
    fun setIconSize(sizePx: Int) {
        this.size = sizePx
        setBounds(0, 0, size, size)
        invalidateSelf()
    }

    /**
     * Set Color with a [Color] integer
     *
     * @param colorInt
     */
    fun setColor(colorInt: Int) {
        paint.color = colorInt
        invalidateSelf()
    }

    /**
     * Set transparency of the drawable.
     *
     * @param alpha A value between 0 and 255 (inclusive)
     */
    override fun setAlpha(alpha: Int) {
        paint.alpha = alpha
        invalidateSelf()
    }

    /**
     * Set the icon character
     *
     * NOTE: This will give black boxes or weird characters if you
     * do not set a valid icon character
     *
     * @param iconChar
     */
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

    /**
     * Builder for [SushiIconDrawable], which itself is private
     * and cannot be directly instantiated
     *
     * @property context An activity/view context to access resources
     */
    class Builder(val context: Context) {
        var drawable: SushiIconDrawable =
            SushiIconDrawable()

        init {
            drawable.apply {
                paint.typeface = ResourcesCompat.getFont(context, R.font.wasabi)
                paint.textAlign = Paint.Align.CENTER
                paint.isUnderlineText = false
                paint.color = ResourceThemeResolver.getThemedColorFromAttr(context, android.R.attr.textColorPrimary)
                paint.isAntiAlias = true
            }
        }

        /**
         * Set size with a dimen resource id
         *
         * @param sizeRes
         */
        fun setIconSizeRes(@DimenRes sizeRes: Int): Builder {
            setIconSize(
                context.resources.getDimensionPixelSize(sizeRes)
            )
            return this
        }

        /**
         * Set size in pixels
         *
         * @param sizePx
         */
        fun setIconSize(sizePx: Int): Builder {
            drawable.setIconSize(sizePx)
            return this
        }


        /**
         * Set color with a color resource ID
         *
         * @param colorResId
         */
        fun setColorRes(@ColorRes colorResId: Int): Builder {
            drawable.setColor(
                ContextCompat.getColor(context, colorResId)
            )
            return this
        }

        /**
         * Set Color with a [Color] integer
         *
         * @param colorInt
         */
        fun setColor(@ColorInt colorInt: Int): Builder {
            drawable.setColor(colorInt)
            return this
        }

        /**
         * Set transparency of the drawable.
         *
         * @param alpha A value between 0 (transparent) and 1 (opaque)
         */
        fun setAlpha(alpha: Float): Builder {
            var a = alpha
            if (a > 1) a = 1f;
            if (a < 0) a = 0f;

            drawable.alpha = (a * 255).toInt()
            return this
        }

        /**
         * Set transparency of the drawable.
         *
         * @param alpha A value between 0 and 255 (inclusive)
         */
        fun setAlpha(alpha: Int): Builder {
            drawable.alpha = alpha
            return this
        }

        /**
         * Set the icon's character (refer to Wasabi's character map)
         * using a string resource
         *
         * @param resId Resource id of the string
         */
        fun setIconStringRes(@StringRes resId: Int): Builder {
            setIconChar(
                context.resources.getString(resId)
            )
            return this
        }

        /**
         * Set the icon character
         *
         * NOTE: This will give black boxes or weird characters if you
         * do not set a valid icon character
         *
         * @param iconChar
         */
        fun setIconChar(iconChar: String): Builder {
            drawable.setIconChar(iconChar)
            return this
        }

        fun build(): SushiIconDrawable = drawable
    }
}