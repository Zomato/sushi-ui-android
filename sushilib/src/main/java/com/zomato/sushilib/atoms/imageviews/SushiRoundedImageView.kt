package com.zomato.sushilib.atoms.imageviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import android.util.Log
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.views.RoundedView
import com.zomato.sushilib.utils.view.OutlineType
import com.zomato.sushilib.utils.view.SushiViewOutlineProvider

/**
 * created by championswimmer on 04/04/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class SushiRoundedImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr), RoundedView {

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
        scaleType = ScaleType.CENTER_CROP

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

    override fun setScaleType(scaleType: ScaleType?) {
        if (scaleType != ScaleType.CENTER_CROP) {
            Log.e(TAG, "Only CENTER_CROP ScaleType supported, ignoring $scaleType")
        }
        super.setScaleType(ScaleType.CENTER_CROP)
    }

    companion object {
        private const val TAG = "SushiRoundedImageView"
    }

}