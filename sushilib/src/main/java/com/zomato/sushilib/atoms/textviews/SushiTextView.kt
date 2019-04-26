package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.support.annotation.StyleRes
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.utils.text.TextFormatUtils
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemeWrappedContext

open class SushiTextView @JvmOverloads constructor(
    ctx: Context?, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : AppCompatTextView(
    getThemeWrappedContext(ctx, defStyleRes),
    attrs, defStyleAttr
) {

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTextView,
            defStyleAttr,
            defStyleRes
        )?.let {
            val sushiFontWeight = it.getInt(R.styleable.SushiTextView_sushiFontWeight, -1)
            if (sushiFontWeight != -1) {
                setTextAppearance(TextFormatUtils.sushiFontWeightToTextAppearance(sushiFontWeight))
            }
            it.recycle()
        }
    }

    override fun setTextAppearance(resId: Int) {
        @Suppress("DEPRECATION")
        super.setTextAppearance(context, resId)
    }

    fun setCompoundDrawableColor(color: Int) {
        compoundDrawables.forEach { d ->
            d?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
        compoundDrawablesRelative.forEach { d ->
            d?.setColorFilter(color, PorterDuff.Mode.SRC_IN)
        }
    }

}