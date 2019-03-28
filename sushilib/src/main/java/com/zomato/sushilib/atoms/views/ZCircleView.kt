package com.zomato.sushilib.atoms.views

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.zomato.sushilib.utils.graphics.BitmapUtils

/**
 * created by championswimmer on 26/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
class ZCircleView : View {
    private val mPaintBackground = Paint()
    private var mCanvasSize = 0

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) :
            this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
            this(context, attrs, defStyleAttr, 0)

    constructor(
        context: Context?,
        attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

        mPaintBackground.isAntiAlias = true

        outlineProvider = ZViewOutlineProvider(OutlineType.CIRCLE)
        clipToOutline = true
        invalidateOutline()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCanvasSize = Math.min(w, h)
    }

    override fun draw(canvas: Canvas?) {
        // 1. Setup the background paint now, to use in onDraw
        if (background != null) {
            mPaintBackground.apply {
                shader = BitmapUtils.getShaderForBitmap(
                    BitmapUtils.drawableToBitmap(background),
                    this@ZCircleView,
                    ImageView.ScaleType.CENTER_CROP
                )
            }
        }

        // 2. Set background to transparent, to prevent drawing it
        background = null

        // 3. draw will call onDraw internally
        super.draw(canvas)
    }

    @SuppressLint("CanvasSize")
    override fun onDraw(canvas: Canvas?) {
        canvas?.let {
            if (!isInEditMode) {
                mCanvasSize = Math.min(it.height, it.width)
            }
            canvas.drawCircle(
                (mCanvasSize / 2).toFloat(),
                (mCanvasSize / 2).toFloat(),
                (mCanvasSize / 2).toFloat(),
                mPaintBackground
            )
        }
    }


}