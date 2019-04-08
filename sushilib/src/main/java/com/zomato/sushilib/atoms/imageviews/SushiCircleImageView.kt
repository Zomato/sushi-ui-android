package com.zomato.sushilib.atoms.imageviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import android.widget.ImageView.ScaleType.CENTER_CROP
import com.zomato.sushilib.utils.view.OutlineType
import com.zomato.sushilib.utils.view.SushiViewOutlineProvider


/**
 * created by championswimmer on 28/03/19
 * Copyright (c) 2019 Zomato Media Pvt. Ltd.
 */
class SushiCircleImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0, defStyleRes: Int = 0
) : ImageView(context, attrs, defStyleAttr, defStyleRes) {
    private val mPreviewPaint = Paint().apply {
        this.style = Paint.Style.FILL
        this.isAntiAlias = true
        this.color = Color.DKGRAY
    }
    init {
        scaleType = CENTER_CROP
        outlineProvider = SushiViewOutlineProvider(
            OutlineType.CIRCLE,
            paddingOutside = true
        )
        clipToOutline = true

    }

    override fun setScaleType(scaleType: ScaleType?) {
        if (scaleType != ScaleType.CENTER_CROP) {
            Log.e(TAG, "Only CENTER_CROP ScaleType supported, ignoring $scaleType")
        }
        super.setScaleType(ScaleType.CENTER_CROP)
    }

    companion object {
        @JvmField
        val TAG = "SushiCircleImageView"
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