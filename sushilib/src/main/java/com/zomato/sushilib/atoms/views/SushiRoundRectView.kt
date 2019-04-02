package com.zomato.sushilib.atoms.views

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.zomato.sushilib.R

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */

class SushiRoundRectView : View {
    private var mCornerRadius: Float = 0f
    private val imageOutlineProvider = SushiViewOutlineProvider()
    private var mBorderPaint = Paint()

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) :
            this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            this(context, attrs, defStyleAttr, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.isAntiAlias = true

        context?.obtainStyledAttributes(
            attrs, R.styleable.SushiRoundRectView, defStyleAttr, 0
        )?.let { ta ->

            mCornerRadius = ta.getDimensionPixelSize(
                R.styleable.SushiRoundRectView_sushiCornerRadius,
                0
            ).toFloat().takeIf { it > 0 } ?: 0f

            ta.recycle()
        }

        outlineProvider = imageOutlineProvider
        clipToOutline = true
        notifyOutlineProvider()

    }

    private fun notifyOutlineProvider() {
        imageOutlineProvider.apply {
            cornerRadius = mCornerRadius
        }
        invalidateOutline()
    }

    fun setCornerRadius(cornerRadius: Float) {
        mCornerRadius = cornerRadius
        notifyOutlineProvider()
    }

    fun getCornerRadius(): Float {
        return mCornerRadius
    }
}