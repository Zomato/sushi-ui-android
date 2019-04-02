package com.zomato.sushilib.atoms.imageviews

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.ImageView.ScaleType.CENTER_CROP
import android.widget.ImageView.ScaleType.CENTER_INSIDE
import com.zomato.sushilib.atoms.views.OutlineType
import com.zomato.sushilib.atoms.views.SushiViewOutlineProvider
import com.zomato.sushilib.utils.graphics.BitmapUtils


/**
 * created by championswimmer on 28/03/19
 * Copyright © 2019 Zomato Media Pvt. Ltd.
 */
class SushiCircleImageView : ImageView {

    private val mPaintBackground = Paint()
    private val mPaintImage = Paint()
    private var mDrawable: Drawable? = null
    private var mBitmap: Bitmap? = null
    private var mCanvasSize = 0

    constructor(context: Context?)
            : this(context, null)

    constructor(context: Context?, attrs: AttributeSet?)
            : this(context, attrs, 0)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : this(context, attrs, defStyleAttr, 0)

    constructor(
        context: Context?, attrs: AttributeSet?,
        defStyleAttr: Int, defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {

        mPaintBackground.isAntiAlias = true
        mPaintImage.isAntiAlias = true

        scaleType = CENTER_CROP
        clipToOutline = true
        outlineProvider = SushiViewOutlineProvider(OutlineType.CIRCLE)
    }

    override fun setScaleType(scaleType: ScaleType?) {
        if (scaleType != CENTER_CROP && scaleType != CENTER_INSIDE) {
            throw IllegalArgumentException(
                """|ScaleType $scaleType supported.
                   |Use ScaleType.CENTER_CROP or ScaleType.CENTER_INSIDE"""
                    .trimMargin()
            )
        }
        super.setScaleType(scaleType)
    }

    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
        mPaintBackground.apply {
            this.color = color
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mCanvasSize = Math.min(w, h)
        if (mBitmap != null) {
            updateShader()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        loadBitmap()
    }

    @SuppressLint("CanvasSize")
    override fun onDraw(canvas: Canvas?) {


        if (mBitmap == null) {
            return
        }

        canvas?.let {
            if (!isInEditMode) {
                mCanvasSize = Math.min(it.height, it.width)
            }

            // Draw background
            canvas.drawCircle(
                (mCanvasSize / 2).toFloat(),
                (mCanvasSize / 2).toFloat(),
                (mCanvasSize / 2).toFloat(),
                mPaintBackground
            )

            // Draw Image
            canvas.drawCircle(
                (mCanvasSize / 2).toFloat(),
                (mCanvasSize / 2).toFloat(),
                (mCanvasSize / 2).toFloat(),
                mPaintImage
            )
        }
    }

    private fun loadBitmap() {
        if (mDrawable == drawable) return

        mDrawable = drawable
        mBitmap = BitmapUtils.drawableToBitmap(drawable)
        updateShader()
    }

    private fun updateShader() {
        if (mBitmap == null) return

        mBitmap?.let { bmp ->
            val shader = BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)

            var scale = 0f
            var dx = 0f
            var dy = 0f

            when (scaleType) {
                CENTER_CROP -> {
                    if (bmp.width * height > width * bmp.height) {
                        scale = height / bmp.height.toFloat()
                        dx = (width - bmp.width * scale) * 0.5f
                    } else {
                        scale = width / bmp.width.toFloat()
                        dy = (height - bmp.height * scale) * 0.5f
                    }
                }
                CENTER_INSIDE -> {
                    if (bmp.width * height < width * bmp.height) {
                        scale = height / bmp.height.toFloat()
                        dx = (width - bmp.width * scale) * 0.5f
                    } else {
                        scale = width / bmp.width.toFloat()
                        dy = (height - bmp.height * scale) * 0.5f
                    }
                }
            }
            shader.setLocalMatrix(Matrix().apply {
                setScale(scale, scale)
                postTranslate(dx, dy)
            })
            mPaintImage.shader = shader
        }
    }
}