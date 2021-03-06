package com.zomato.sushilib.molecules.tags

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import com.zomato.sushilib.R
import com.zomato.sushilib.atoms.textviews.SushiIconTextView


/**
 * Created by prempal on 2019-04-30.
 */
open class SushiCircleIconView @JvmOverloads constructor(
    ctx: Context, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = R.attr.circleIconTextStyle
) : SushiIconTextView(ctx, attrs, defStyleAttr) {

    init {
        background = ContextCompat.getDrawable(context, R.drawable.sushi_rounded_icon_bg)
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SushiCircleIconView,
            defStyleAttr,
            0
        ).let {
            it.getColorStateList(R.styleable.SushiCircleIconView_strokeColor)?.let {
                setStrokeColor(it)
            } ?: setStrokeColor(
                ColorStateList.valueOf(
                    it.getColor(
                        R.styleable.SushiCircleIconView_strokeColor,
                        ContextCompat.getColor(context, R.color.sushi_grey_200)
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