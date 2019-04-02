package com.zomato.sushilib.atoms.tagviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiIconHelper

class SushiBigRatingTag : SushiTagView {

    private val disableBorderPaint = Paint().apply {
        style = Paint.Style.STROKE
        color = resources.getColor(R.color.disabled_text_color)
        strokeWidth = resources.getDimension(R.dimen.sushi_spacing_nano)
    }

//    private val backgroundColo

    // todo defstyleattr fix
    constructor(context: Context?) : super(context, null) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context,
        attrs,
        0,
        R.style.SushiTheme_TagAppearance_Capsule
    ) {
        init(context, attrs, 0, R.style.SushiTheme_TagAppearance_Capsule)
    }

    private fun init(
        context: Context?,
        attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0
    ) {
        context?.let {
            val starIconDrawable = SushiIconHelper.getIconDrawableEditor(context)
                .icon(resources.getString(R.string.icon_filled_star))
                .sizePx(textSize.toInt() - resources.getDimensionPixelOffset(R.dimen.sushi_spacing_micro))
                .colorInt(resources.getColor(R.color.sushi_white)).apply()
            compoundDrawablePadding = resources.getDimensionPixelOffset(R.dimen.sushi_spacing_between_small)
            setCompoundDrawables(null, null, starIconDrawable, null)
        }
    }


    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (!isSelected) {
            canvas?.drawRoundRect(
                0f,
                0f,
                width.toFloat(),
                height.toFloat(),
                cornerRadius,
                cornerRadius,
                disableBorderPaint
            )
        }
    }


}