package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import com.zomato.sushilib.R


/**
 * Created by prempal on 2019-04-30.
 */
open class SushiCircleIconTextView @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.circleIconTextViewStyle
) : SushiIconTextView(ctx, attrs, defStyleAttr) {

    init {
        background = ContextCompat.getDrawable(context, R.drawable.sushi_rounded_icon_bg)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiCircleIconTextView,
            defStyleAttr,
            0
        ).let {
            it.getColorStateList(R.styleable.SushiCircleIconTextView_strokeColor)?.let {
                setStrokeColor(it)
            } ?: setStrokeColor(
                ColorStateList.valueOf(
                    it.getColor(
                        R.styleable.SushiCircleIconTextView_strokeColor,
                        ContextCompat.getColor(context, R.color.sushi_grey_400)
                    )
                )
            )
            it.recycle()
        }
    }

    fun setStrokeColor(colorStateList: ColorStateList) {
        background.mutate()
        (background as GradientDrawable).setStroke(
            resources.getDimensionPixelSize(R.dimen.sushi_stoke_width_small),
            colorStateList
        )
        (background as GradientDrawable).setColor(Color.TRANSPARENT)
    }
}