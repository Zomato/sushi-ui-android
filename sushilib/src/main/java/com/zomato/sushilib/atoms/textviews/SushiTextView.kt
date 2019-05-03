package com.zomato.sushilib.atoms.textviews

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.os.Build
import android.support.annotation.AttrRes
import android.support.annotation.StyleRes
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import com.zomato.sushilib.R
import com.zomato.sushilib.annotations.FontWeight
import com.zomato.sushilib.utils.text.TextFormatUtils
import com.zomato.sushilib.utils.theme.ResourceThemeResolver.getThemeWrappedContext
import com.zomato.sushilib.utils.widgets.TextViewUtils

open class SushiTextView @JvmOverloads constructor(
    ctx: Context?, attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : AppCompatTextView(
    getThemeWrappedContext(ctx, defStyleRes),
    attrs, defStyleAttr
) {
    @get:FontWeight @setparam:FontWeight
    var textFontWeight: Int = FontWeight.REGULAR
    set(value) {
        field = value
        setTextAppearance(TextFormatUtils.textFontWeightToTextAppearance(value))
    }

    init {
        context?.theme?.obtainStyledAttributes(
            attrs,
            R.styleable.SushiTextView,
            defStyleAttr,
            defStyleRes
        )?.let {
            val attrTextFontWeight = it.getInt(R.styleable.SushiTextView_textFontWeight, -1)
            // Only do this is someone has actually set this attr in xml
            if (attrTextFontWeight != -1) {
                textFontWeight = attrTextFontWeight
            }
            it.recycle()
        }
        TextViewUtils.apply {
            applyDrawables(
                attrs, defStyleAttr,
                currentTextColor,
                textSize.toInt()
            )
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

    override fun setCompoundDrawableTintList(tintList: ColorStateList?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            super.setCompoundDrawableTintList(tintList)
        } else {
            compoundDrawables.forEach { d ->
                d?.setTintList(tintList)
            }
            compoundDrawablesRelative.forEach { d ->
                d?.setTintList(tintList)
            }
        }
    }

}