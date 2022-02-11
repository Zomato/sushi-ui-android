package com.zomato.sushilib.atoms.imageviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.appcompat.widget.AppCompatImageView
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.OutlineType
import com.zomato.sushilib.atoms.views.RoundedView
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemeWrappedContext
import com.zomato.sushilib.utils.view.SushiViewOutlineProvider

/**
 * created by championswimmer on 04/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
open class SushiRoundedImageView @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0, @StyleRes defStyleRes: Int = 0
) : AppCompatImageView(
    getThemeWrappedContext(ctx, defStyleRes),
    attrs, defStyleAttr
), RoundedView {

    private val mPreviewPaint = Paint().apply {
        this.style = Paint.Style.FILL
        this.isAntiAlias = true
        this.color = Color.DKGRAY
    }

    init {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiRoundedImageView,
            defStyleAttr,
            defStyleRes
        ).let {
            outlineProvider = SushiViewOutlineProvider(
                OutlineType.ROUNDED_RECT,
                0f,
                true
            )
            val cr = it.getDimension(R.styleable.SushiRoundedImageView_cornerRadius, 0f)
            val crtl = it.getDimension(
                R.styleable.SushiRoundedImageView_cornerRadiusTopLeft, 0f
            )
            val crtr = it.getDimension(
                R.styleable.SushiRoundedImageView_cornerRadiusTopRight, 0f
            )
            val crbr = it.getDimension(
                R.styleable.SushiRoundedImageView_cornerRadiusBottomRight, 0f
            )
            if (cr > 0f) {
                cornerRadius = cr
            }
            it.recycle()
        }

        context.theme.obtainStyledAttributes(
            attrs, R.styleable.SushiCircleImageView, defStyleAttr, defStyleRes
        ).let {
            if (it.getInt(R.styleable.SushiCircleImageView_android_scaleType, -1) == -1) {
                // If not set, default to CENTER_CROP
                scaleType = ScaleType.CENTER_CROP
            }

            it.recycle()
        }

    }

    override fun onDraw(canvas: Canvas?) {
        if (!isInEditMode) {
            // At run time just rely on the outline provider
            return super.onDraw(canvas)
        } else {
            // For previewing purpose draw a grey shape
            (outlineProvider as SushiViewOutlineProvider).let {
                canvas?.drawRoundRect(
                    it.left.toFloat(), it.top.toFloat(), it.right.toFloat(), it.bottom.toFloat(),
                    cornerRadius, cornerRadius,
                    mPreviewPaint
                )
            }
        }
    }

    companion object {
        private const val TAG = "SushiRoundedImageView"
    }

}