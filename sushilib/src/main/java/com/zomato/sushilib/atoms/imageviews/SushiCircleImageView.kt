package com.zomato.sushilib.atoms.imageviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.annotation.AttrRes
import android.support.annotation.StyleRes
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.widget.ImageView.ScaleType.CENTER_CROP
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.OutlineType
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemeWrappedContext
import com.zomato.sushilib.utils.view.SushiViewOutlineProvider


/**
 * created by championswimmer on 28/03/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
open class SushiCircleImageView @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : AppCompatImageView(
    getThemeWrappedContext(ctx, defStyleRes),
    attrs, defStyleAttr
) {
    private val mPreviewPaint = Paint().apply {
        this.style = Paint.Style.FILL
        this.isAntiAlias = true
        this.color = Color.DKGRAY
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs, R.styleable.SushiCircleImageView, defStyleAttr, defStyleRes
        ).let {
            if (it.getInt(R.styleable.SushiCircleImageView_android_scaleType, -1) == -1) {
                // If not set, default to CENTER_CROP
                scaleType = CENTER_CROP
            }

            it.recycle()
        }
        outlineProvider = SushiViewOutlineProvider(
            OutlineType.CIRCLE,
            paddingOutside = false
        )
        clipToOutline = true

    }

    override fun onDraw(canvas: Canvas?) {
        if (!isInEditMode) {
            // At run time just rely on the outline provider
            return super.onDraw(canvas)
        } else {
            // For previewing purpose draw a grey shape
            (outlineProvider as SushiViewOutlineProvider).let {
                canvas?.drawOval(
                    it.left.toFloat(), it.top.toFloat(), it.right.toFloat(), it.bottom.toFloat(),
                    mPreviewPaint
                )
            }
        }
    }
}